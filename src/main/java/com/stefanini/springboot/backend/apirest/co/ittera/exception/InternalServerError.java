package com.stefanini.springboot.backend.apirest.co.ittera.exception;


public class InternalServerError extends RuntimeException {

	public InternalServerError (String mensaje) {
		super(mensaje);
	}

}
