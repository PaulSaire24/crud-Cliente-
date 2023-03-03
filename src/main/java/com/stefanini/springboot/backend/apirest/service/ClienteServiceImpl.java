package com.stefanini.springboot.backend.apirest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefanini.springboot.backend.apirest.co.ittera.exception.InternalServerError;
import com.stefanini.springboot.backend.apirest.co.ittera.exception.ModelNotFoundException;
import com.stefanini.springboot.backend.apirest.dao.IClienteDao;
import com.stefanini.springboot.backend.apirest.models.entity.Cliente;

import jakarta.transaction.Transactional;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	@Transactional
	public List<Cliente> findAll()  {
		return clienteDao.findAll();
	}

	@Override
	@Transactional
	public Cliente findById(Integer id) {
		
		Optional<Cliente> cli = clienteDao.findById(id);
		
		if(cli.isEmpty()) {
			throw new ModelNotFoundException("El cliente no existe en la  base de datos");
		}
		
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente)  {
		
		if(cliente.getNombre()==null||cliente.getNombre().isEmpty()) {
			throw new InternalServerError("El cliente no peude tener el nombre vacio o nulo");
		}	
		
		if(cliente.getApellido()==null||cliente.getApellido().isEmpty()) {
			throw new InternalServerError("El cliente no peude tener el apellido vacio o nulo");
		}	
		
		List<Cliente> lista =  clienteDao.findAll();
		
		for(int i = 0;i<lista.size();i++) {
			if(cliente.getDni().equals(lista.get(i).getDni())) {
				throw new InternalServerError("El ciente no puede tener un dni igual a otro cliente");
			}
		}
		
		return clienteDao.save(cliente);	
		
	}

	@Override
	@Transactional
	public void delete(Integer id)  {
		
		Optional<Cliente> cli = clienteDao.findById(id);
		
		if(cli.isEmpty()) {
			throw new ModelNotFoundException("El cliente que desea eliminar no existe");
		}

	 clienteDao.deleteById(id);
		
	}


}
