package com.example.demo.security.service.jwt.impl;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

import java.util.Objects;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.security.service.UserAuthenticationService;
import com.example.demo.security.service.jwt.TokenService;
import com.example.demo.service.UserService;
import com.google.common.collect.ImmutableMap;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Profile("jwt")
public class TokenAuthenticationService implements UserAuthenticationService {

	@NonNull
	private TokenService tokens;

	@NonNull
	private UserService users;

	@Override
	public Optional<String> login(final String username, final String password) {
		return users
				.findByUsername(username)
				.filter(user -> Objects.equals(password, user.getPassword()))
				.map(user -> tokens.expiring(ImmutableMap.of("username", username)));
	}

	@Override
	public Optional<User> findByToken(final String token) {
		return Optional
				.of(tokens.verify(token))
				.map(map -> map.get("username"))
				.flatMap(users::findByUsername);
	}

	@Override
	public void logout(final User user) {
		// Nothing to doy
	}
}
