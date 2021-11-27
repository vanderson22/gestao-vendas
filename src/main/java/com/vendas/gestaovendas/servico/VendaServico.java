package com.vendas.gestaovendas.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.vendas.gestaovendas.entidades.Venda;
import com.vendas.gestaovendas.repositorio.VendaRepositorio;

@Service
public class VendaServico {

	@Autowired
	private VendaRepositorio venRepositorio;

	public List<Venda> listar() {

		return venRepositorio.findAll();
	}

	public Page<Venda>  listarTodosPaginado(PageRequest req) {
		 
		return venRepositorio.findAll(req);
	}

}
