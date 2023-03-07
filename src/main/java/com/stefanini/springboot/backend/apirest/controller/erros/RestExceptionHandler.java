package com.stefanini.springboot.backend.apirest.controller.erros;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.stefanini.springboot.backend.apirest.co.ittera.exception.ClienteNotFound;
import com.stefanini.springboot.backend.apirest.co.ittera.exception.InternalServerError;


@ControllerAdvice()
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ClienteNotFound.class)
	public ResponseEntity<Object> handleClienteNotFound(ClienteNotFound ex, WebRequest request){
		
		return new ResponseEntity<Object>(new ApiError(ex.getMessage(),HttpStatus.NOT_FOUND,LocalDateTime.now()),HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(InternalServerError.class)
	public ResponseEntity<Object> handleClienteInternalServerErro(InternalServerError ex, WebRequest request){
		
		return new ResponseEntity<Object>(new ApiError(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,LocalDateTime.now()),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
