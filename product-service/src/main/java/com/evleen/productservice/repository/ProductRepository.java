package com.evleen.productservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.evleen.productservice.entity.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
	public Optional<Product> findByKey(String key);
}
