package com.evleen.orderservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.evleen.orderservice.dto.ProductDecrementDto;
import com.evleen.orderservice.entity.Product;

import jakarta.validation.Valid;

@FeignClient(name = "product-service")
public interface ProductFeignClient {
	@GetMapping("/api/products/by-key")
	public Product getProductByKey(@RequestParam String key);

	@PostMapping("/api/products/decrement-stock")
	public ResponseEntity<Boolean> decrementProductStock(@Valid @RequestBody ProductDecrementDto productDecrementDto);
}
