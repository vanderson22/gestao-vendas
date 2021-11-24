package com.vendas.gestaovendas.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

}
