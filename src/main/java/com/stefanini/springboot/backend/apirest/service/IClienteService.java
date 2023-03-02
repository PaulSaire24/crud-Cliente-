package com.stefanini.springboot.backend.apirest.service;

import java.util.List;

import com.stefanini.springboot.backend.apirest.models.entity.Cliente;

public interface IClienteService {
	
	public List<Cliente> findAll();
	
	public Cliente findById(Integer id);
	
	public Cliente save(Cliente cliente);
	
	public void delete (Integer id);
}
