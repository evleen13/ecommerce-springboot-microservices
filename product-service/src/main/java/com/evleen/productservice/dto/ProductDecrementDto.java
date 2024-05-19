package com.evleen.productservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDecrementDto {
	@NotEmpty(message = "Product Key is required")
	private String key;

	@NotNull(message = "Product quantity is required")
	@Min(value = 0, message = "Product quantity must be greater than or equal to 0")
	private int quantity;
}
