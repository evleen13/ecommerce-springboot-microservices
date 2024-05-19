package com.evleen.orderservice.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
	@NotBlank(message = "User email is required")
	private String userEmail;
	@NotNull(message = "Products are required")
	private List<ProductDto> products;
}
