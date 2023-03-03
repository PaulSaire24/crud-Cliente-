package com.stefanini.springboot.backend.apirest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stefanini.springboot.backend.apirest.controller.ClienteRestController;
import com.stefanini.springboot.backend.apirest.models.entity.Cliente;
import com.stefanini.springboot.backend.apirest.service.IClienteService;


@WebMvcTest(ClienteRestController.class)
public class ClienteControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IClienteService clienteService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	void getCliente() throws Exception {
		
		Cliente cliente =  new Cliente();
		cliente.setId(1);
		cliente.setNombre("Pedro");
		cliente.setApellido("Ordoñes Avila");
		cliente.setDni("89564123");
		cliente.setEdad(45);
		cliente.setSexo("Masculino");
		cliente.setFechaCrea(new Date());
		
		when(clienteService.findById(1)).thenReturn(cliente);
		
		mockMvc.perform(get("/api/cliente/get/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.mensaje").value("Cliente encontrado correctamente"));			
		
	}
	
	@Test
	void saveCliente() throws Exception {
		
		Cliente cliente =  new Cliente();
		cliente.setId(1);
		cliente.setNombre("Pedro");
		cliente.setApellido("Ordoñes Avila");
		cliente.setDni("89564123");
		cliente.setEdad(45);
		cliente.setSexo("Masculino");
		cliente.setFechaCrea(new Date());
		
		when(clienteService.save(any(Cliente.class))).thenReturn(cliente);
		
		this.mockMvc.perform(post("/api/cliente/create").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cliente)))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
	}
	
	@Test
	void deleteCliente() throws Exception {
		
		Cliente cliente =  new Cliente();
		cliente.setId(1);
		cliente.setNombre("Pedro");
		cliente.setApellido("Ordoñes Avila");
		cliente.setDni("89564123");
		cliente.setEdad(45);
		cliente.setSexo("Masculino");
		cliente.setFechaCrea(new Date());
		
		Map<String, Object> response = new HashMap<>();
		response.put("mensaje", "Cliente eliminado correctamente"); 
		
		this.mockMvc.perform(delete("/api/cliente/delete/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(response)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.mensaje").value("Cliente eliminado correctamente"));	
	}
	
	@Test
	void updateCliente() throws Exception {
		
		Cliente cliente =  new Cliente();
		cliente.setId(1);
		cliente.setNombre("Pedro");
		cliente.setApellido("Ordoñes Avila");
		cliente.setDni("89564123");
		cliente.setEdad(45);
		cliente.setSexo("Masculino");
		cliente.setFechaCrea(new Date());
		
		when(clienteService.findById(1)).thenReturn(cliente);
		when(clienteService.save(any(Cliente.class))).thenReturn(cliente);
		
		this.mockMvc.perform(put("/api/cliente/update/1").contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(cliente)))
					.andExpect(status().isCreated())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.mensaje").value("Cliente actualizado correctamente"));	
	}
	
	@Test
	void listAllCliente() throws Exception {
		
		Cliente cliente =  new Cliente();
		cliente.setId(1);
		cliente.setNombre("Pedro");
		cliente.setApellido("Ordoñes Avila");
		cliente.setDni("89564123");
		cliente.setEdad(45);
		cliente.setSexo("Masculino");
		cliente.setFechaCrea(new Date());
		
		List<Cliente> listCliente = new ArrayList<>();
		
		listCliente.add(cliente);
		
		
		when(clienteService.findAll()).thenReturn(listCliente);
		
		
		this.mockMvc.perform(get("/api/cliente/lista").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(listCliente)))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
	}
	
}
