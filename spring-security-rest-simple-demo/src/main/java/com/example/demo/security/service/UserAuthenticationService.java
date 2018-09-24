package com.example.demo.security.service;

import java.util.Optional;

import com.example.demo.entity.User;

public interface UserAuthenticationService {

	public Optional<String> login(String username, String password);

	public Optional<User> findByToken(String token);

	public void logout(User user);
}
