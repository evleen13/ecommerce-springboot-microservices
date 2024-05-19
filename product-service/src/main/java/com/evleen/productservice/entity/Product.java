package com.evleen.productservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "products")
public class Product {
	@Id
	private String id;

	@Indexed(unique = true)
	@NotEmpty(message = "Product Key is required")
	private String key;

	@NotEmpty(message = "Product name is required")
	private String name;

	@NotEmpty(message = "Product description is required")
	private String description;

	@NotNull(message = "Product price is required")
    @Min(value = 1, message = "Product price must be greater than 0")
	private double price;

	@NotNull(message = "Product quantityInStock is required")
    @Min(value = 0, message = "Product quantityInStock must be greater than or equal to 0")
	private int quantityInStock;
}
