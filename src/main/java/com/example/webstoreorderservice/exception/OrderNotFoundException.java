package com.example.webstoreorderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5947336999070719351L;

	public OrderNotFoundException(String message) {
		super(message);
	}
}
