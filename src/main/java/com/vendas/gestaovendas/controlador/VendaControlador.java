package com.vendas.gestaovendas.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vendas.gestaovendas.entidades.Venda;
import com.vendas.gestaovendas.entidades.dto.ClienteVendaRespostaDto;
import com.vendas.gestaovendas.entidades.dto.ClienteVendasRequisicaoDto;
import com.vendas.gestaovendas.servico.VendaServico;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/clientes")
@Api(tags = "clientes-venda")
public class VendaControlador {

	@Autowired
	private VendaServico vendaServico;

	@GetMapping(path = "/todas/vendas")
	@ApiOperation(value = "listar", tags = "clientes-venda")
	public ResponseEntity<List<Venda>> listar() {

		return ResponseEntity.ok().body(vendaServico.listar());
	}

	@ApiOperation(value = "Listar-paginado")
	@GetMapping(params = { "page", "size" }, path = "/todas/vendas")
	public ResponseEntity<Page<Venda>> listarTodos(@RequestParam("page") int page, @RequestParam("size") int size) {
		PageRequest req = PageRequest.of(page, size);
		return ResponseEntity.ok(vendaServico.listarTodosPaginado(req));
	}

	@ApiOperation(value = "criar-venda")
	@PostMapping(path = "/{codigoCliente}")
	public ResponseEntity<ClienteVendaRespostaDto> criar(@PathVariable(name = "codigoCliente") Long codigoCliente,
			@RequestBody(required = true) ClienteVendasRequisicaoDto vendaDto) {

		return ResponseEntity.status(HttpStatus.CREATED).body(vendaServico.salvar(codigoCliente, vendaDto));
	}
}
