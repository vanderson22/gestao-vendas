package com.vendas.gestaovendas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendas.gestaovendas.entidades.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

}
