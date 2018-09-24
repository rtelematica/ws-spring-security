package com.example.demo.controller;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.security.service.UserAuthenticationService;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/users")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
public class SecuredUsersController {

	@NonNull
	private UserAuthenticationService authentication;

	@GetMapping("/current")
	public User getCurrent(@AuthenticationPrincipal final User user) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth: " + auth);
		return user;
	}

	@GetMapping("/logout")
	boolean logout(@AuthenticationPrincipal final User user) {
		authentication.logout(user);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth: " + auth);
		return true;
	}
}
