package com.example.demo.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotExistException extends RuntimeException {

	private static final long serialVersionUID = -6483198924596682993L;

	public UserNotExistException(String message) {
		super(message);
	}
}
