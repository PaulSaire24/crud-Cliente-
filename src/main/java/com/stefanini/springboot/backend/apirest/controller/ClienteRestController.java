package com.stefanini.springboot.backend.apirest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefanini.springboot.backend.apirest.models.entity.Cliente;
import com.stefanini.springboot.backend.apirest.service.IClienteService;

@RequestMapping("/api")
@RestController
public class ClienteRestController {
	
	@Autowired
	private IClienteService clienteServce;
	
	@GetMapping("/cliente/lista")
	List<Cliente> getAllListCliente(){
		return clienteServce.findAll();
	}
	
	@PostMapping("/cliente/create")
	ResponseEntity<?> createCliente(@RequestBody Cliente cliente) throws Exception{
		Cliente clienteNew =  null;
		Map<String, Object> response = new HashMap<>();
	
		cliente.setFechaCrea(new Date());
		clienteNew = clienteServce.save(cliente);		
		
		response.put("mensaje", "Cliente registrado correctamente.");
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	
	}
	
	@GetMapping("/cliente/get/{id}")
	ResponseEntity<?> getCliente(@PathVariable Integer id) throws Exception{
		
		Map<String, Object> response = new HashMap<>();
		Cliente ClienteNew = clienteServce.findById(id);
			
		response.put("cliente",ClienteNew);
		response.put("mensaje","Cliente encontrado correctamente");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	@PutMapping("/cliente/update/{id}")
	ResponseEntity<?> getCliente(@RequestBody Cliente cliente,@PathVariable Integer id){
		Cliente clienteActual = clienteServce.findById(id);
		Cliente clienteUpdate =  null;
		
		Map<String, Object> response = new HashMap<>();
		
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setEdad(cliente.getEdad());
			clienteActual.setSexo(cliente.getSexo());
			clienteActual.setFechaCrea(cliente.getFechaCrea());
			clienteActual.setDni(cliente.getDni());
			
			clienteUpdate =  clienteServce.save(clienteActual);			
		response.put("cliente", clienteUpdate);
		response.put("mensaje", "Cliente actualizado correctamente");
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/cliente/delete/{id}")
	ResponseEntity<?> deleteCliente(@PathVariable Integer id) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		clienteServce.delete(id);	
		response.put("mensaje", "Cliente eliminado correctamente");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
}
