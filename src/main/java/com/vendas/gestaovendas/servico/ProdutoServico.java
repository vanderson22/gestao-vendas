package com.vendas.gestaovendas.servico;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vendas.gestaovendas.controlador.ProdutoController;
import com.vendas.gestaovendas.entidades.Produto;
import com.vendas.gestaovendas.repositorio.ProdutoRepositorio;

@Service
public class ProdutoServico {

	@Autowired
	private ProdutoRepositorio repo;
	
	final static Logger LOGGER = LoggerFactory.getLogger(ProdutoController.class);


	public List<Produto> listarTodos() {

		return repo.findAll();
	}

	public Page<Produto> listarTodosPaginado(Pageable page) {

		return repo.findAll(page);
	}

	public Produto buscarPorId(Long codigo) {
		Optional<Produto> opt = repo.findById(codigo);
		if (!opt.isPresent())
			throw new EmptyResultDataAccessException(2);
		return opt.get();
	}

	public void remover(Long codigo) {

		Produto produto = buscarPorId(codigo);
		LOGGER.trace("Produto encontrado {} - prosseguindo com a remoção " , produto);
		repo.delete(produto);

	}

}
