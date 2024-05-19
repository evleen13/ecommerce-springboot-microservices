package com.evleen.userservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.evleen.userservice.dto.UserDto;
import com.evleen.userservice.entity.User;
import com.evleen.userservice.exception.UserNotFoundException;
import com.evleen.userservice.repository.UserRepository;

public class UserServiceTests {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	private User user;

	@BeforeEach
	public void setUp() {
		user = new User();
		user.setId(1L);
		user.setName("Test User");
		user.setEmail("test@example.com");
		user.setPhoneNumber("1234567890");
		user.setPassword("password1234");
		MockitoAnnotations.openMocks(this);
		// Initialize other required fields of the User entity
	}

	@Test
	public void testFindAllUsers() {
		when(userRepository.findAll()).thenReturn(Arrays.asList(user));
		ResponseEntity<List<User>> response = userService.findAllUsers();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
		assertEquals("Test User", response.getBody().get(0).getName());
	}

	@Test
	public void testRegisterUser() {
		when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
		when(userRepository.save(any(User.class))).thenReturn(user);
		ResponseEntity<?> response = userService.registerUser(user);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		UserDto responseBody = (UserDto) response.getBody();
		assertEquals("Test User", responseBody.getName());
	}

	@Test
	public void testFindUserByIdFound() {
		when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
		ResponseEntity<?> response = userService.findUserById(1L);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		UserDto responseBody = (UserDto) response.getBody();
		assertEquals("Test User", responseBody.getName());
	}

	@Test
	public void testFindUserByIdNotFound() {
		when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> userService.findUserById(1L));
	}

	@Test
	public void testUpdateUser() {
		when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
		when(userRepository.save(any(User.class))).thenReturn(user);
		ResponseEntity<?> response = userService.updateUser(user, 1L);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		UserDto responseBody = (UserDto) response.getBody();
		assertEquals("Test User", responseBody.getName());
	}

	@Test
	public void testDeleteUserByIdFound() {
		when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
		ResponseEntity<?> response = userService.deleteUserById(1L);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(userRepository, times(1)).delete(user);
	}

	@Test
	public void testDeleteUserByIdNotFound() {
		when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> userService.deleteUserById(1L));
	}

	@Test
	public void testFindUserByEmail() {
		when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
		UserDto result = userService.findUserByEmail("test@example.com");
		assertEquals("Test User", result.getName());
	}
}
