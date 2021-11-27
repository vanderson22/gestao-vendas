package com.vendas.gestaovendas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendas.gestaovendas.entidades.Venda;

public interface VendaRepositorio extends JpaRepository<Venda, Long> {

//	@Transactional(readOnly = true)
//	Page<Venda> findAll(PageRequest req);

}
