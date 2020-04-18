package com.synechron.assignment.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.synechron.assignment.entity.Policy;


@ControllerAdvice
public class PolicyRestExceptionHandler {

	
		
		@ExceptionHandler
		public ResponseEntity<PolicyRestErrorResponse> handleException(PolicyNotFoundException exe)
		{
			PolicyRestErrorResponse error = new PolicyRestErrorResponse();
			error.setStatus(HttpStatus.NOT_FOUND.value());
			error.setMessage(exe.getMessage());
			error.setTimestamp(System.currentTimeMillis());
			return new ResponseEntity<PolicyRestErrorResponse>(error, HttpStatus.NOT_FOUND);
		}
		
		//to handle all type of exceptions like string type use generic exception
		
		@ExceptionHandler
		public ResponseEntity<PolicyRestErrorResponse> handleExp(Exception exe)
		{
			PolicyRestErrorResponse error = new PolicyRestErrorResponse();
			error.setStatus(HttpStatus.BAD_REQUEST.value());
			error.setMessage(exe.getMessage());
			error.setTimestamp(System.currentTimeMillis());
			return new ResponseEntity<PolicyRestErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}
		
		@ExceptionHandler({MethodArgumentTypeMismatchException.class})
		public ResponseEntity<PolicyRestErrorResponse> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex) {
			System.out.println(ex.getClass().getName());
			
			PolicyRestErrorResponse error = new PolicyRestErrorResponse();
			error.setStatus(HttpStatus.BAD_REQUEST.value());
			error.setMessage("Invalid Input...Please try entering valid number");
			error.setTimestamp(System.currentTimeMillis());
			
			return new ResponseEntity<PolicyRestErrorResponse>( error ,  HttpStatus.BAD_REQUEST);
		}
}
