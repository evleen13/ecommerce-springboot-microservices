package com.evleen.orderservice.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "orders")
public class Order {
	@Id
	private String id;

	@NotNull(message = "User is required")
	private User user;

	@Pattern(regexp = OrderStatusConstants.PLACED + "|" + OrderStatusConstants.PROCESSING + "|"
			+ OrderStatusConstants.SHIPPED + "|" + OrderStatusConstants.DELIVERED + "|"
			+ OrderStatusConstants.CANCELLED)
	@NotBlank(message = "Please specify a valid status")
	private String status;

	@NotNull(message = "Products is required")
	private List<@NotNull Product> products;

//	@NotBlank(message = "Price is required")
//	@Min(value = 1, message = "Price should not have negative value")
	private double totalPrice;

//	@NotNull(message = "createdAt is required")
//	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime createdAt;

//	@NotNull(message = "updatedAt is required")
//	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime updatedAt;
}
