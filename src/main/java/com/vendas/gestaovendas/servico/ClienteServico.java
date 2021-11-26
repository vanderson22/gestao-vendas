package com.vendas.gestaovendas.servico;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendas.gestaovendas.entidades.Cliente;
import com.vendas.gestaovendas.excecao.RegraNegocioException;
import com.vendas.gestaovendas.repositorio.ClienteRepositorio;

@Service
public class ClienteServico {

	@Autowired
	private ClienteRepositorio clienteRepositorio;

	public List<Cliente> listarTodas() {

		return clienteRepositorio.findAll();
	}

	public Cliente buscarPorId(Long id) {

		Optional<Cliente> optional = clienteRepositorio.findById(id);

		if (optional.isEmpty())
			throw new RegraNegocioException(String.format("Cliente não encontrado"));

		return optional.get();
	}

	public Cliente criar(@Valid Cliente cliente) {

		return clienteRepositorio.save(cliente);
	}

}
