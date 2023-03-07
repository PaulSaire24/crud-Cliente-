package com.stefanini.springboot.backend.apirest.co.ittera.exception;

public class ClienteNotFound extends RuntimeException{
	public ClienteNotFound(String message) {
		super(message);
	}

}
