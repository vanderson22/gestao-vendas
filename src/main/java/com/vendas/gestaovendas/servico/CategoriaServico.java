package com.vendas.gestaovendas.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendas.gestaovendas.entidades.Categoria;
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
}
