package com.vendas.gestaovendas.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vendas.gestaovendas.entidades.Categoria;
import com.vendas.gestaovendas.excecao.RegraNegocioException;
import com.vendas.gestaovendas.repositorio.CategoriaRepositorio;

@Service
public class CategoriaServico {

	@Autowired
	private CategoriaRepositorio repo;

	public List<Categoria> listarTodas() {

		return repo.findAll();
	}

	public Optional<Categoria> buscarPorId(Long id) {

		return repo.findById(id);
	}

	public Categoria salvar(Categoria categoria) {

		return repo.save(categoria);
	}

	public Categoria atualizarCategoria(Long codigo, Categoria categoria) {
		Optional<Categoria> categoriaOpt = categoriaExiste(codigo);

		if (categoriaOpt.isPresent()) {
			Categoria catAtualizar = categoriaOpt.get();
			BeanUtils.copyProperties(categoria, catAtualizar, "codigo");

			return repo.save(catAtualizar);
		} else {
			throw new EmptyResultDataAccessException("Não existe dado para código informado " + codigo, 1);
		}
	}

	private Optional<Categoria> categoriaExiste(Long codigo) {
		return buscarPorId(codigo);
	}

	public void deleteById(Long codigo) {
		repo.deleteById(codigo);
	}

	private void validarCategoriaDuplicada(Categoria categoria) {
		Categoria catEncontrada = repo.findByNome(categoria.getNome());
		if (catEncontrada != null && !catEncontrada.getCodigo().equals(categoria.getCodigo())) {
			throw new RegraNegocioException(String.format("A Categoria %s já está cadastrada", categoria.getNome()));
		}

	}
}
