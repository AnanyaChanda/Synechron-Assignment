package com.synechron.assignment.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND  , reason ="This customer is not found in the system" )
public class PolicyNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	
	public PolicyNotFoundException(String message) {
		
		super(message);
		System.out.println("Inside policyNot found"+ message);
		
	}

}
