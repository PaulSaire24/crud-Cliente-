package com.stefanini.springboot.backend.apirest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stefanini.springboot.backend.apirest.dao.IClienteDao;
import com.stefanini.springboot.backend.apirest.models.entity.Cliente;
import com.stefanini.springboot.backend.apirest.service.ClienteServiceImpl;
import com.stefanini.springboot.backend.apirest.service.IClienteService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SpringBootBackendApirestApplicationTests {
	
	//solo permite a los clases
	@InjectMocks
	ClienteServiceImpl clienteService;
	
	//si permie a la interface
	@Mock
	IClienteDao clienteDao;
	
	@Test
	void primerTest() {
		
		
		List<Cliente> listCliente = new ArrayList<>();
		
		Cliente cliente  =  new Cliente();
		
		cliente.setNombre("Gerardo");
		cliente.setApellido("Carpio Leon");
		cliente.setDni("89564123");
		cliente.setEdad(26);
		cliente.setSexo("Masculino");
		cliente.setFechaCrea(new Date());
		
		listCliente.add(cliente);
		
		when(clienteDao.findAll()).thenReturn(listCliente);
		
		List<Cliente> listClienteTest = clienteService.findAll();
		assertNotNull(listClienteTest);
		assertTrue(listClienteTest.size()>0);
		assertEquals(listClienteTest.get(0).getNombre(), "Gerardo");
	}
	
	@Test
	void segundoTest() {	
		
		Cliente cliente = new Cliente();
		
		cliente.setId(40);
		cliente.setNombre("Julia");
		cliente.setApellido("Castillo Jopa");
		cliente.setDni("45236941");
		cliente.setEdad(45);
		cliente.setSexo("Femenino");
		cliente.setFechaCrea(new Date());
		
		when(clienteDao.save(cliente)).thenReturn(cliente);
		
		Cliente clienteTest = clienteService.save(cliente);
		assertNotNull(clienteTest);
		assertTrue(clienteTest!=null);
		assertEquals(clienteTest.getDni(), "45236941");
	}
	
	@Test
	void tercerTest() {	
		
		verify(clienteDao).deleteById(1);
	}
	
	@Test
	void cuartoTest() {	
		
		Cliente cliente = new Cliente();
		
		cliente.setId(40);
		cliente.setNombre("Claudia");
		cliente.setApellido("Estrada Varane");
		cliente.setDni("70221978");
		cliente.setEdad(24);
		cliente.setSexo("Femenino");
		cliente.setFechaCrea(new Date());
		
		when(clienteDao.findById(cliente.getId())).thenReturn(Optional.of(cliente));
		
		Cliente clienteTest = clienteService.findById(40);
		
		assertNotNull(clienteTest);
		assertTrue(clienteTest!=null);
		assertEquals(clienteTest.getSexo(), "Femenino");
		assertNotEquals(clienteTest, null);
	}
	
	
	
	

}
