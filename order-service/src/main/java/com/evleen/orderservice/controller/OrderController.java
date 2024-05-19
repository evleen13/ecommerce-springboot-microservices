package com.evleen.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.evleen.orderservice.dto.OrderRequestDto;
import com.evleen.orderservice.entity.Order;
import com.evleen.orderservice.service.OrderService;

import jakarta.validation.Valid;

@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;

	@GetMapping("/orders")
	public List<Order> getAllOrders() {
		return orderService.getOrders();
	}

	@PostMapping("/orders")
	public Order createNewOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
		return orderService.createOrder(orderRequestDto);
	}

	@GetMapping("/orders/{id}")
	public Order getOrderById(@Valid @PathVariable String id) {
		return orderService.retrieveOrderById(id);
	}

	@PatchMapping("/orders/updatestatus/{id}")
	public Order updateStatus(@PathVariable String id) {
		return orderService.updateStatusByOrderId(id);
	}
	
	@PostMapping("/orders/cancelorder/{id}")
	public Order cancelOrder(@PathVariable String id) {
		return orderService.cancelOrderByOrderId(id);
	}
}
