/**
 * 
 */
package com.ik.warehouse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Ravi
 *
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<Response> blogNotFoundException(ResourceNotFoundException resourceNotFountException) {
		log.error(resourceNotFountException.getMessage());
		return new ResponseEntity<Response>(new Response(resourceNotFountException.getMessage()), HttpStatus.NOT_FOUND);
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	private class Response {
        String message;
    }
}
