package com.vendas.gestaovendas.servico;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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

	final Logger LOGGER = LoggerFactory.getLogger(ProdutoServico.class);

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

	public Produto criar(Produto p, Long codigoCategoria) {

		validarCategoria(p.getCategoria(), codigoCategoria);

		return repo.save(p);
	}

	public List<Produto> buscarPorCategoriaCodigo(Long codigo) {
		validarCategoria(new Categoria(codigo, null), codigo);

		return repo.findByCategoriaCodigoOrderByDescricaoDesc(codigo);

	}

	private Categoria validarCategoria(Categoria categoria, Long codigoCategoria) {

		LOGGER.info(String.format("A Produto %s não foi encontrada", categoria));

		if (categoria == null || categoria.getCodigo() == null || codigoCategoria == null)
			throw new RegraNegocioException("Categoria não pode ser nula");

		if (!codigoCategoria.equals(categoria.getCodigo())) {
			throw new RegraNegocioException(
					String.format("A Categoria informada não pode ser diferente  categoria no Path[%s]  - Categoria no objeto [%s]",
							categoria.getCodigo(), codigoCategoria));
		}

		Optional<Categoria> optional = catRepo.buscarPorId(categoria.getCodigo());
		if (optional.isEmpty()) {
			LOGGER.info(String.format("A Categoria %s não foi encontrada", categoria.getCodigo()));
			throw new EmptyResultDataAccessException(
					String.format("A Categoria %s não foi encontrada", categoria.getCodigo()), 1);
		}

		return optional.get();
	}

	private Produto validarProduto(Produto produto, Long codigo, Boolean validacaoDuplicado) {

		if (produto == null || produto.getCodigo() == 0)
			throw new RegraNegocioException("Produto não pode ser nulo");

		Optional<Produto> optional = repo.buscarPorCodigo(produto.getCodigo());
		if (!optional.isPresent()) {
			throw new EmptyResultDataAccessException(
					String.format("A Produto %s não foi encontrada", produto.getCodigo()), 1);
		}

		if (Boolean.TRUE.equals(validacaoDuplicado)
				&& (!repo.findByDescricao(produto.getDescricao()).getCodigo().equals(codigo)))
			throw new RegraNegocioException("Já existe produto cadastrado com a mesma descricao ");

		return optional.get();

	}

	public Produto atualizar(Long codigoCategoria, Long codigo, Produto produto) {

		validarCategoria(new Categoria(codigoCategoria, null), codigoCategoria);
		Produto produtoValidado = validarProduto(produto, codigo, true);

		// Cópiar tudo para o produtoValidado exceto o código.
		BeanUtils.copyProperties(produto, produtoValidado, "codigo");
		produtoValidado.getCategoria().setCodigo(codigoCategoria);

		return repo.save(produtoValidado);
	}

}
