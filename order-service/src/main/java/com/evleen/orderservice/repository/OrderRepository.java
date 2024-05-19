package com.evleen.orderservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.evleen.orderservice.entity.Order;

public interface OrderRepository extends MongoRepository<Order, String> {

}
