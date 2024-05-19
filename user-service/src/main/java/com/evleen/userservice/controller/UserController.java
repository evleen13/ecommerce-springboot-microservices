package com.evleen.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evleen.userservice.dto.UserDto;
import com.evleen.userservice.entity.User;
import com.evleen.userservice.service.UserService;

import jakarta.validation.Valid;

@RestController
@Validated
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		return userService.findAllUsers();
	}

	@PostMapping("/users")
	public ResponseEntity<?> registerUser(@RequestBody @Valid User user, BindingResult bindingResult) {
		return userService.registerUser(user);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {
		return userService.findUserById(id);
	}
	
	@GetMapping("/users/by-email")
	public UserDto getUserByEmail(@RequestParam String email) {
		return userService.findUserByEmail(email);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<?> updateUserDetails(@Valid @RequestBody User updatedUser, @PathVariable Long id) {
		return userService.updateUser(updatedUser, id);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> removeUser(@PathVariable Long id) {
		return userService.deleteUserById(id);
	}

}
