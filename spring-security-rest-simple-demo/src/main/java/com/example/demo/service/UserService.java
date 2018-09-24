package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.User;

public interface UserService {

	public User save(User user);

	public void delete(User user);

	public Optional<User> find(String id);

	public Optional<User> findByUsername(String username);
}
