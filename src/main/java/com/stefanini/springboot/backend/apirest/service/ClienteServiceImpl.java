package com.stefanini.springboot.backend.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefanini.springboot.backend.apirest.dao.IClienteDao;
import com.stefanini.springboot.backend.apirest.models.entity.Cliente;

import jakarta.transaction.Transactional;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	@Transactional
	public List<Cliente> findAll() {
		return clienteDao.findAll();
	}

	@Override
	@Transactional
	public Cliente findById(Integer id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
	 clienteDao.deleteById(id);
		
	}


}
