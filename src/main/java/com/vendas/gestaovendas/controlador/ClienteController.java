package com.vendas.gestaovendas.controlador;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendas.gestaovendas.entidades.Cliente;
import com.vendas.gestaovendas.entidades.dto.ClinteResponseDto;
import com.vendas.gestaovendas.servico.ClienteServico;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Cliente")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	final Logger LOGGER = LoggerFactory.getLogger(ProdutoController.class);

	@Autowired
	private ClienteServico clienteServico;

	@ApiOperation(value = "Listar-cliente")
	@GetMapping
	public ResponseEntity<List<Cliente>> listar() {

		return ResponseEntity.ok().body(clienteServico.listarTodas());
	}

	@ApiOperation(value = "BuscarPorId")
	@GetMapping("/{codigo}")
	public ResponseEntity<ClinteResponseDto> buscarPorId(@PathVariable(name = "codigo") Long codigo) {

		return ResponseEntity.ok()
				.body(ClinteResponseDto.converterParaClienteResponseDto(clienteServico.buscarPorId(codigo)));
	}

	@ApiOperation(value = "criar", nickname = "Salvar-cliente")
	@PostMapping()
	public ResponseEntity<Cliente> criar(@Valid @RequestBody() Cliente cliente) {

		Cliente clienteNovo = clienteServico.criar(cliente);
		LOGGER.trace("Cliente Novo criado {} ", clienteNovo);

		return ResponseEntity.status(HttpStatus.CREATED).body(clienteNovo);
	}

}
