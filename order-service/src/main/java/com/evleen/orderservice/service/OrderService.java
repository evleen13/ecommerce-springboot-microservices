package com.evleen.orderservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evleen.orderservice.dto.OrderRequestDto;
import com.evleen.orderservice.dto.ProductDecrementDto;
import com.evleen.orderservice.dto.ProductDto;
import com.evleen.orderservice.entity.Order;
import com.evleen.orderservice.entity.OrderStatusConstants;
import com.evleen.orderservice.entity.Product;
import com.evleen.orderservice.entity.User;
import com.evleen.orderservice.exception.InvalidOrderStatusException;
import com.evleen.orderservice.exception.OrderNotFoundException;
import com.evleen.orderservice.exception.ProductOutOfStockException;
import com.evleen.orderservice.feignclient.ProductFeignClient;
import com.evleen.orderservice.feignclient.UserFeignClient;
import com.evleen.orderservice.repository.OrderRepository;

import jakarta.validation.Valid;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserFeignClient userFeignClient;

	@Autowired
	private ProductFeignClient productFeignClient;

	public List<Order> getOrders() {
		return orderRepository.findAll();
	}

	@Transactional
	public Order createOrder(@Valid OrderRequestDto orderRequestDto) {
		User userByEmail = userFeignClient.getUserByEmail(orderRequestDto.getUserEmail());
		List<ProductDto> productDto = orderRequestDto.getProducts();
		double totalPrice = 0;

		List<Product> products = productDto.stream().map(p -> {
			Product productByKey = productFeignClient.getProductByKey(p.getKey());
			productByKey.setQuantity(p.getQuantity());
			if (productByKey.getQuantityInStock() < productByKey.getQuantity()) {
				// check stock and if we don't have enough quantity throw an error
				throw new ProductOutOfStockException("The product" + productByKey.getKey() + " is out of stock");
			}
			return productByKey;
		}).toList();

		// calculate totalPrice
		for (Product p : products) {
			totalPrice += p.getQuantity() * p.getPrice();
			// reduce the quantityInStock by quantity bought
			ProductDecrementDto productDecrementDto = new ProductDecrementDto(p.getKey(), p.getQuantity());
			productFeignClient.decrementProductStock(productDecrementDto);
		}

		Order order = new Order();
		order.setStatus(OrderStatusConstants.PLACED);
		order.setProducts(products);
		order.setUser(userByEmail);
		order.setTotalPrice(totalPrice);
		order.setCreatedAt(LocalDateTime.now());
		order.setUpdatedAt(LocalDateTime.now());

		Order savedOrder = orderRepository.save(order);
		return savedOrder;
	}

	public Order retrieveOrderById(@Valid String id) {
		Optional<Order> byId = orderRepository.findById(id);
		if (byId.isEmpty()) {
			throw new OrderNotFoundException("Order with id " + id + " not found");
		}
		return byId.get();
	}

	public Order updateStatusByOrderId(String id) {
		Optional<Order> byId = orderRepository.findById(id);
		if (byId.isEmpty()) {
			throw new OrderNotFoundException("Order with id " + id + " not found");
		}
		Order order = byId.get();
		String currentStatus = order.getStatus();
		String updatedStatus = "";
		switch (currentStatus) {
		case OrderStatusConstants.PLACED:
			updatedStatus = OrderStatusConstants.PROCESSING;
			break;
		case OrderStatusConstants.PROCESSING:
			updatedStatus = OrderStatusConstants.SHIPPED;
			break;
		case OrderStatusConstants.SHIPPED:
			updatedStatus = OrderStatusConstants.DELIVERED;
			break;
		default:
			throw new InvalidOrderStatusException(
					"Order status cannot be modified, as current order status is " + currentStatus);
		}
		order.setStatus(updatedStatus);
		order.setUpdatedAt(LocalDateTime.now());
		// update status in db
		orderRepository.save(order);
		return order;
	}

	public Order cancelOrderByOrderId(String id) {
		Optional<Order> byId = orderRepository.findById(id);
		if (byId.isEmpty()) {
			throw new OrderNotFoundException("Order with id " + id + " not found");
		}
		Order order = byId.get();
		String currentStatus = order.getStatus();
		if (currentStatus != OrderStatusConstants.CANCELLED | currentStatus != OrderStatusConstants.DELIVERED) {
			order.setStatus(OrderStatusConstants.CANCELLED);
			order.setUpdatedAt(LocalDateTime.now());
			orderRepository.save(order);
		}
		return order;
	}

}
