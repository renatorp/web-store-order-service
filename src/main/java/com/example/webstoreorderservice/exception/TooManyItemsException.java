package com.example.webstoreorderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TooManyItemsException extends RuntimeException {
	
	private static final long serialVersionUID = 8024592994553629711L;

	public TooManyItemsException(Integer maxItems) {
		super("Your order has exceeded the the max allowed: " + maxItems);
	}

}
