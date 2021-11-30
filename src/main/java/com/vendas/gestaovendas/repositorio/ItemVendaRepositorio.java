package com.vendas.gestaovendas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendas.gestaovendas.entidades.ItemVenda;

public interface ItemVendaRepositorio extends JpaRepository<ItemVenda, Long> {

}
