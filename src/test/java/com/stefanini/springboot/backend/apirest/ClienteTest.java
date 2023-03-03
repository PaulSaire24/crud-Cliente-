package com.stefanini.springboot.backend.apirest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.stefanini.springboot.backend.apirest.dao.IClienteDao;
import com.stefanini.springboot.backend.apirest.models.entity.Cliente;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ClienteTest {
	
	@Autowired
	private IClienteDao clienteDao;
	
	
	
	@Test
	public void testClienteSave() {
		Cliente cliente =  new Cliente();
		cliente.setNombre("Pedro");
		cliente.setApellido("Ordoñes Avila");
		cliente.setDni("89564123");
		cliente.setEdad(45);
		cliente.setSexo("Masculino");
		cliente.setFechaCrea(new Date());
		
		Cliente clienteNew =   clienteDao.save(cliente);
		assertNotNull(clienteNew);
	}
	
	@Test
	public void findByIdCliente() {
		Integer idCliente = 3;
		Optional<Cliente> clieteFound =  clienteDao.findById(idCliente);
		
		assertThat(clieteFound.get().getNombre()).isEqualTo("joel");	
	}
	
	@Test
	public void NotfindByIdCliente() {
		Integer idCliente = 2;
		Optional<Cliente> clieteFound =  clienteDao.findById(idCliente);
		
		assertNull(clieteFound.get());
	}
	
	@Test
	public void updateCliente() {
		Integer idCliente = 2;
		Optional<Cliente> clieteFound =  clienteDao.findById(idCliente);		
		assertNull(clieteFound.get());
	}
	

}
