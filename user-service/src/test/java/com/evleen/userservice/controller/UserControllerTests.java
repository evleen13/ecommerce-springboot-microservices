package com.evleen.userservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.evleen.userservice.dto.UserDto;
import com.evleen.userservice.entity.User;
import com.evleen.userservice.service.UserService;

public class UserControllerTests {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	@BeforeEach
	public void setUp() {
		// Initialize Mockito annotations
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllUsers() {
		List<User> userList = new ArrayList<>();

		when(userService.findAllUsers()).thenReturn(new ResponseEntity<>(userList, HttpStatus.OK));

		ResponseEntity<List<User>> responseEntity = userController.getAllUsers();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(userList, responseEntity.getBody());

		// userService.findAllUsers() is called exactly once
		verify(userService, times(1)).findAllUsers();
	}

	@Test
	public void testRegisterUser() {
		User user = new User();
		BindingResult bindingResult = mock(BindingResult.class);

		when(userService.registerUser(user)).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));

		ResponseEntity<?> responseEntity = userController.registerUser(user, bindingResult);

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

		// Verify that userService.registerUser(user) was called exactly once
		verify(userService, times(1)).registerUser(user);
	}

	@Test
	public void testGetUserById() {
		Long userId = 1L;
//		UserDto user = new UserDto();
//		ResponseEntity<?> responseEntity2 = new ResponseEntity<UserDto>(user, HttpStatus.OK);
		// .thenReturn(responseEntity2);
		when(userService.findUserById(userId)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
		ResponseEntity<?> responseEntity = userController.getUserById(userId);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//		assertEquals(user, responseEntity.getBody());

		// Verify that userService.findUserById(userId) was called exactly once with the
		// correct argument
		verify(userService, times(1)).findUserById(userId);
	}

	@Test
	public void testGetUserByEmail() {
		String email = "test@example.com";
		UserDto userDto = new UserDto();

		when(userService.findUserByEmail(email)).thenReturn(userDto);

		UserDto result = userController.getUserByEmail(email);

		assertEquals(userDto, result);

		// Verify that userService.findUserByEmail(email) was called exactly once with
		// the correct argument
		verify(userService, times(1)).findUserByEmail(email);
	}

	@Test
	public void testUpdateUserDetails() {
		Long userId = 1L;
		User updatedUser = new User();

		when(userService.updateUser(updatedUser, userId)).thenReturn(new ResponseEntity<>(HttpStatus.OK));

		ResponseEntity<?> responseEntity = userController.updateUserDetails(updatedUser, userId);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Verify that userService.updateUser(updatedUser, userId) was called exactly
		// once with the correct arguments
		verify(userService, times(1)).updateUser(updatedUser, userId);
	}

	@Test
	public void testRemoveUser() {
		Long userId = 1L;

		when(userService.deleteUserById(userId)).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

		ResponseEntity<?> responseEntity = userController.removeUser(userId);

		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

		// Verify that userService.deleteUserById(userId) was called exactly once with
		// the correct argument
		verify(userService, times(1)).deleteUserById(userId);
	}
}