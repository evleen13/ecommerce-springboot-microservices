package com.evleen.orderservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.evleen.orderservice.entity.User;

@FeignClient(name = "user-service")
public interface UserFeignClient {
	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id);
	
	@GetMapping("/users/by-email")
	public User getUserByEmail(@RequestParam String email);
}
