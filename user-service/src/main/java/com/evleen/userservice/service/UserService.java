package com.evleen.userservice.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.evleen.userservice.dto.UserDto;
import com.evleen.userservice.entity.User;
import com.evleen.userservice.exception.UserAlreadyRegisteredException;
import com.evleen.userservice.exception.UserNotFoundException;
import com.evleen.userservice.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);


	public ResponseEntity<List<User>> findAllUsers() {
		logger.info("Finding all users");
		List<User> allUsers = userRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(allUsers);
	}

	public ResponseEntity<?> registerUser(@Valid User user) {
		Optional<User> userByEmail = userRepository.findByEmail(user.getEmail());
		// check if email is valid

		if (userByEmail.isPresent()) {
			logger.info("User already registered");
			throw new UserAlreadyRegisteredException(
					"The user with email " + user.getEmail() + " is already registered");
		}
		// Hash the password
		String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        // set the hashed password 
		System.out.println("Compare: " + BCrypt.checkpw(user.getPassword(), hashedPassword));
		user.setPassword(hashedPassword);

		User savedUser = userRepository.save(user);
		UserDto userdto = new UserDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail(),
				savedUser.getPhoneNumber());
		return ResponseEntity.status(HttpStatus.CREATED).body(userdto);
	}

	public ResponseEntity<?> findUserById(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isEmpty()) {
			logger.info("User not found");
			throw new UserNotFoundException("User with id " + id + " not found, please use correct user id");
		}
		User user = userOptional.get();
		UserDto dto = new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber());
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	public ResponseEntity<?> updateUser(@Valid User updatedUser, Long id) {
		Optional<User> currentUserOptional = userRepository.findById(id);
		if (currentUserOptional.isEmpty()) {
			throw new UserNotFoundException("User with id " + id + " not found, please use correct user id");
		}
		User currentUser = currentUserOptional.get();
		currentUser.setEmail(updatedUser.getEmail());
		currentUser.setName(updatedUser.getName());
		currentUser.setPhoneNumber(updatedUser.getPhoneNumber());
		userRepository.save(currentUser);
		UserDto userdto = new UserDto(currentUser.getId(), currentUser.getName(), currentUser.getEmail(),
				currentUser.getPhoneNumber());
		return ResponseEntity.status(HttpStatus.OK).body(userdto);
	}

	public ResponseEntity<?> deleteUserById(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isEmpty()) {
			throw new UserNotFoundException("User with id " + id + " not found, please use correct user id");
		}
		userRepository.delete(userOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("User successfully removed");
	}

	public UserDto findUserByEmail(String email) {
		Optional<User> userOptional = userRepository.findByEmail(email);
		if (userOptional.isEmpty()) {
			throw new UserNotFoundException("User with email " + email + " not found, please use correct user email");
		}
		User user = userOptional.get();
		UserDto dto = new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber());
//		return ResponseEntity.status(HttpStatus.OK).body(dto);
		return dto;
	}

}
