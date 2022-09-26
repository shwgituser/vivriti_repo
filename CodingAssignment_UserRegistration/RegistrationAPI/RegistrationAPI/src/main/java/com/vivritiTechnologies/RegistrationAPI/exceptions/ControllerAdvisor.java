package com.vivritiTechnologies.RegistrationAPI.exceptions;

import java.util.LinkedHashMap;
import java.util.Map;
//import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	
    public ResponseEntity<Object> handleElementNotFoundException(
    		EmptyResultDataAccessException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "Element could not be found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}
