package com.evleen.userservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UserServiceApplication.class)
public class UserServiceApplicationTests {

	@Autowired
	private UserServiceApplication userServiceApplication;

	@Test
	public void contextLoads() {
		assertThat(userServiceApplication).isNotNull();
	}
}
