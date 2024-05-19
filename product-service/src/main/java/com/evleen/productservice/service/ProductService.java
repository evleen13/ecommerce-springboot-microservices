package com.evleen.productservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.evleen.productservice.dto.ProductDecrementDto;
import com.evleen.productservice.entity.Product;
import com.evleen.productservice.exception.DuplicateProductKeyException;
import com.evleen.productservice.exception.ProductNotAvailableException;
import com.evleen.productservice.exception.ProductNotFoundException;
import com.evleen.productservice.repository.ProductRepository;

import jakarta.validation.Valid;

@Service
public class ProductService {
	@Autowired
	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> retrieveAllProducts() {
		return productRepository.findAll();
	}

	public Product retrieveProductById(String id) {
		Optional<Product> productById = productRepository.findById(id);
		if (productById.isEmpty()) {
			throw new ProductNotFoundException("Product not found with id: " + id);
		}
		return productById.get();
	}

	public Product saveProduct(@Valid Product product) {
		Optional<Product> productByKey = productRepository.findByKey(product.getKey());
		if (productByKey.isPresent()) {
			throw new DuplicateProductKeyException("A product with key \'" + product.getKey() + "\' already exists: ");
		}
		Product savedProduct = productRepository.save(product);
		return savedProduct;
	}

	public ResponseEntity<String> removeProductById(String id) {
		Optional<Product> productById = productRepository.findById(id);
		if (productById.isEmpty()) {
			throw new ProductNotFoundException("Product not found with id: " + id);
		}
		productRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
	}

	public Product retrieveProductByKey(String key) {
		Optional<Product> productById = productRepository.findByKey(key);
		if (productById.isEmpty()) {
			throw new ProductNotFoundException("Product not found with key: " + key);
		}
		return productById.get();
	}

	public ResponseEntity<Boolean> decrementQuantityOnStock(@Valid ProductDecrementDto productDecrementDto) {
		Optional<Product> productById = productRepository.findByKey(productDecrementDto.getKey());
		if (productById.isEmpty()) {
			throw new ProductNotFoundException("Product not found with key: " + productDecrementDto.getKey());
		}
		Product product = productById.get();
		int quantityOnStock = product.getQuantityInStock();
		int quantity = productDecrementDto.getQuantity();
		int updatedQuantity = quantityOnStock - quantity;
		if (quantityOnStock < quantity && updatedQuantity < 0) {
			throw new ProductNotAvailableException(
					quantity + " units of " + productDecrementDto.getKey() + " are not available at the moment");
		}
		product.setQuantityInStock(updatedQuantity);
		productRepository.save(product);
		return ResponseEntity.status(HttpStatus.OK).body(true);
	}
}
