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
import com.vendas.gestaovendas.entidades.Categoria;
import com.vendas.gestaovendas.entidades.Produto;
import com.vendas.gestaovendas.excecao.RegraNegocioException;
import com.vendas.gestaovendas.repositorio.ProdutoRepositorio;

@Service
public class ProdutoServico {

	@Autowired
	private ProdutoRepositorio repo;

	@Autowired
	private CategoriaServico catRepo;

	final static Logger LOGGER = LoggerFactory.getLogger(ProdutoController.class);

	public List<Produto> listarTodos() {

		return repo.findAll();
	}

	public Page<Produto> listarTodosPaginado(Pageable page) {

		return repo.findAll(page);
	}

	public Produto buscarPorId(Long codigo) {
		Optional<Produto> opt = repo.buscarPorCodigo(codigo);
		if (!opt.isPresent())
			throw new EmptyResultDataAccessException(2);
		return opt.get();
	}

	public void remover(Long codigo) {

		Produto produto = buscarPorId(codigo);
		LOGGER.trace("Produto encontrado {} - prosseguindo com a remoção ", produto);

		repo.delete(produto);

	}

	public Produto criar(Produto p) {

		validarCategoria(p.getCategoria());

		return repo.save(p);
	}

	public List<Produto> buscarPorCategoriaCodigo(Long codigo) {
		validarCategoria(new Categoria(codigo, null));

		return repo.findByCategoriaCodigoOrderByDescricaoDesc(codigo);

	}

	private void validarCategoria(Categoria categoria) {

		if (categoria == null || categoria.getCodigo() == null)
			throw new RegraNegocioException("Categoria não pode ser nula");

		Optional<Categoria> optional = catRepo.buscarPorId(categoria.getCodigo());
		if (!optional.isPresent()) {
			throw new EmptyResultDataAccessException(
					String.format("A Categoria %s não foi encontrada", categoria.getCodigo()), 1);
		}

	}

}
