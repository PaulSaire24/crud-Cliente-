package com.stefanini.springboot.backend.apirest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stefanini.springboot.backend.apirest.models.entity.Cliente;

public interface IClienteDao extends JpaRepository<Cliente,Integer> {

}
