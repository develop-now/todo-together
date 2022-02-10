package com.dina.todotogether;

import com.dina.todotogether.data.entity.Role;
import com.dina.todotogether.data.entity.RoleType;
import com.dina.todotogether.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.dina.todotogether.data.entity.RoleType.ROLE_ADMIN;
import static com.dina.todotogether.data.entity.RoleType.ROLE_USER;

@Slf4j
@SpringBootApplication
public class TodotogetherApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodotogetherApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, ROLE_ADMIN ));
			userService.saveRole(new Role(null, ROLE_USER));
		};
	}

}
