package com.vendas.gestaovendas.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.vendas.gestaovendas.entidades.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {

	@Transactional(readOnly = true)
	Page<Produto> findAll(Pageable page);

	@Query("Select p from Produto p where p.codigo=:codigo")
	Optional<Produto> buscarPorCodigo(Long codigo);

	List<Produto> findByCategoriaCodigoOrderByDescricaoDesc(Long codigo);

	Produto findByDescricao(String descricao);

}
