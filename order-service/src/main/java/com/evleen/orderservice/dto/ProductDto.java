package com.evleen.orderservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {
	@NotBlank(message = "Product key is required")
	private String key;
	@NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity should be greater than 0")
	private int quantity;
}
