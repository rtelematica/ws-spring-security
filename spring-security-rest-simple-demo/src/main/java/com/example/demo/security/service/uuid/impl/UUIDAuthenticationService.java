package com.example.demo.security.service.uuid.impl;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.demo.controller.exceptions.UserNotExistException;
import com.example.demo.entity.User;
import com.example.demo.security.service.UserAuthenticationService;
import com.example.demo.service.UserService;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Profile("!jwt")
public class UUIDAuthenticationService implements UserAuthenticationService {

	@NonNull
	private UserService users;

	private Map<String, String> usernameTokenMap = Collections.synchronizedMap(new HashMap<>());
	private Map<String, User> tokenUserMap = Collections.synchronizedMap(new HashMap<>());

	@Override
	public Optional<String> login(final String username, final String password) {

		final String uuid = UUID.randomUUID().toString();

		Optional<User> userOptional = users.findByUsername(username);

		if (!userOptional.isPresent()) {
			throw new UserNotExistException("Cant login provided user.");
		}

		User user = userOptional.get();

		if (!user.getPassword().equals(password)) {
			throw new UserNotExistException("Bad credentials.");
		}

		if (usernameTokenMap.get(username) == null) {
			usernameTokenMap.put(username, uuid);
			tokenUserMap.put(uuid, user);
		} else {
			throw new UserNotExistException("User already logged in.");
		}

		return Optional.of(uuid);
	}

	@Override
	public Optional<User> findByToken(final String token) {
		return Optional.ofNullable(tokenUserMap.get(token));
	}

	@Override
	public void logout(final User user) {
		String uuid = usernameTokenMap.get(user.getUsername());
		usernameTokenMap.remove(user.getUsername());
		tokenUserMap.remove(uuid);
	}
}
