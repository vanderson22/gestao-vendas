package com.vendas.gestaovendas.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vendas.gestaovendas.entidades.Produto;
import com.vendas.gestaovendas.servico.ProdutoServico;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/produtos")
@Api(tags = "Produto")
public class ProdutoController {

	@Autowired
	private ProdutoServico prodServico;

	@ApiOperation(value = "Listar")
	@GetMapping
	public ResponseEntity<List<Produto>> listarTodos() {

		return ResponseEntity.ok(prodServico.listarTodos());
	}

	@ApiOperation(value = "Listar-paginado")
	@GetMapping(params = { "page", "size" })
	public ResponseEntity<Page<Produto>> listarTodos(@RequestParam("page") int page, @RequestParam("size") int size) {
		PageRequest req = PageRequest.of(page, size);
		return ResponseEntity.ok(prodServico.listarTodosPaginado(req));
	}

	@ApiOperation(value = "buscar-id")
	@GetMapping(path = "/{codigo}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable(name = "codigo", required = true) Long codigo) {

		return ResponseEntity.ok(prodServico.buscarPorId(codigo));
	}
}
