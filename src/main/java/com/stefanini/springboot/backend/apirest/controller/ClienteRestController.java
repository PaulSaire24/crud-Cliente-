package com.stefanini.springboot.backend.apirest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
	ResponseEntity<?> createCliente(@RequestBody Cliente cliente){
		Cliente clienteNew =  null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			cliente.setFechaCrea(new Date());
			clienteNew = clienteServce.save(cliente);
		} catch (DataAccessException e) {
			 response.put("mensaje", "Error al realizar el insert en la BD");
	         response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
	         return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Cliente registrado correctamente.");
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	
	}
	
	@GetMapping("/cliente/get/{id}")
	ResponseEntity<?> getCliente(@PathVariable Integer id){
		
		Map<String, Object> response = new HashMap<>();
		
		Cliente ClienteNew = clienteServce.findById(id);
		
		try {
			ClienteNew = clienteServce.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(ClienteNew==null) {
			response.put("mensaje", "Error, no se encontro el cliene en la BD.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}else {
			response.put("cliente",ClienteNew);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		}
	}
	
	@PutMapping("/cliente/update/{id}")
	ResponseEntity<?> getCliente(@RequestBody Cliente cliente,@PathVariable Integer id){
		Cliente clienteActual = clienteServce.findById(id);
		Cliente clienteUpdate =  null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(clienteActual == null) {
			response.put("mensaje", "Error, no se pudo editar: El cliente no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
			}
		
		try {
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setEdad(cliente.getEdad());
			clienteActual.setSexo(cliente.getSexo());
			clienteActual.setFechaCrea(cliente.getFechaCrea());
			
			clienteUpdate =  clienteServce.save(clienteActual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la BD.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Cliente actualizado correctamente");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/cliente/delete/{id}")
	ResponseEntity<?> deleteCliente(@PathVariable Integer id){
		Map<String, Object> response = new HashMap<>();
		
		Cliente clienteBuscado = clienteServce.findById(id);
		
		if(clienteBuscado== null) {
			response.put("mensaje", "Error al eliminar en la BD.");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			clienteServce.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en la BD.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Cliente eliminado correctamente");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
}