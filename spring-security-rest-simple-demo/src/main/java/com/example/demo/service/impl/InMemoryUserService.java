package com.example.demo.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Service
public class InMemoryUserService implements UserService {

	private Map<String, User> users = Collections.synchronizedMap(new HashMap<>());

	@Override
	public User save(final User user) {
		return users.put(user.getId(), user);
	}

	@Override
	public Optional<User> find(final String id) {
		return Optional.ofNullable(users.get(id));
	}

	@Override
	public Optional<User> findByUsername(final String username) {
		return users
				.values()
				.stream()
				.filter(u -> Objects.equals(username, u.getUsername()))
				.findFirst();
	}

	@Override
	public void delete(User user) {
		users.remove(user.getId());
	}
}
