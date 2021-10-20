package com.vendas.gestaovendas.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.vendas.gestaovendas.entidades.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {

	@Transactional(readOnly = true)
	Page<Produto> findAll(Pageable page);

}
