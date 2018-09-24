package com.example.demo.controller;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.exceptions.UserAlreadyRegisteredException;
import com.example.demo.controller.exceptions.UserNotExistException;
import com.example.demo.entity.User;
import com.example.demo.security.service.UserAuthenticationService;
import com.example.demo.service.UserService;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/public/users")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
public class PublicUsersController {

	@NonNull
	private UserAuthenticationService userAuthenticationService;

	@NonNull
	private UserService userService;

	@PostMapping("/register")
	public String register(
			@RequestParam("username") final String username,
			@RequestParam("password") final String password) {

		Optional<User> userOptional = userService.findByUsername(username);

		if (!userOptional.isPresent()) {

			userService.save(
					User.builder()
							.id(username)
							.username(username)
							.password(password)
							.build());
		} else {
			throw new UserAlreadyRegisteredException("User already registered.");
		}

		return "OK";
	}

	@PostMapping("/unregister")
	public String unregister(
			@RequestParam("username") final String username) {

		Optional<User> userOptional = userService.findByUsername(username);

		if (userOptional.isPresent()) {

			userService.delete(userOptional.get());

		} else {
			throw new UserNotExistException("User not exists.");
		}

		return "OK";
	}

	@PostMapping("/login")
	public String login(
			@RequestParam("username") final String username,
			@RequestParam("password") final String password) {

		return userAuthenticationService
				.login(username, password)
				.orElseThrow(() -> new RuntimeException("invalid login and/or password"));
	}
}
