package com.bookhaven.ecom.exceptions;

import java.time.LocalDateTime;
import java.io.IOException;

import org.json.JSONException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.ServletException;

@ControllerAdvice
public class CustomizeResponseExceptionalHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(IOException.class)
	public final ResponseEntity<ErrorDetails> handleIOException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				          ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ValidationException.class)
	public final ResponseEntity<ErrorDetails> handleValidationException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				          ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(JSONException.class)
	public final ResponseEntity<ErrorDetails> handleJSONException(Exception ex, WebRequest request) throws Exception {
	ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				          ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ServletException.class)
	public final ResponseEntity<ErrorDetails> handleServletException(Exception ex, WebRequest request) throws Exception {
	ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				          ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUsernameNotFoundException(Exception ex, WebRequest request) throws Exception {
	ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				          ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public final ResponseEntity<ErrorDetails> handleBadCredentialsException(Exception ex, WebRequest request) throws Exception {
	ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				          ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception {
	ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				          ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
}
