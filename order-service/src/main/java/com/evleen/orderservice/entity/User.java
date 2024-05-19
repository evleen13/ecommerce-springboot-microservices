package com.evleen.orderservice.entity;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class User {
	@Id
	private Long id;

	@NotBlank(message = "Name is required")
	@NotNull(message = "Name is required")
	private String name;

	@NotBlank(message = "Email is required")
	@NotNull(message = "Email is required")
	@Email(message = "Please enter a valid email")
	private String email;

	@NotBlank(message = "Phone Number is required")
	@NotNull(message = "Phone Number is required")
	private String phoneNumber;

	// We can't use json ignore as we expect the password field in request but not
	// in response
//	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//	@NotBlank(message = "Password is required")
//	@NotNull(message = "Password is required")
//	private String password;
}