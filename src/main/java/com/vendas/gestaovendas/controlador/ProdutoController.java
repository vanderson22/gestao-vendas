package com.vendas.gestaovendas.controlador;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vendas.gestaovendas.entidades.Produto;
import com.vendas.gestaovendas.servico.ProdutoServico;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/produtos")
@Api(tags = "Produto")
public class ProdutoController {

	final Logger LOGGER = LoggerFactory.getLogger(ProdutoController.class);

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

	@ApiOperation(value = "buscar-produto-categoria")
	@GetMapping(path = "/categoria/{codigo}")
	public ResponseEntity<List<Produto>> buscarPorCategoriaCodigo(
			@PathVariable(name = "codigo", required = true) Long codigo) {

		return ResponseEntity.ok(prodServico.buscarPorCategoriaCodigo(codigo));
	}

	@ApiOperation(value = "atualizar")
	@PutMapping(path = "/{codigo}/categoria/{codigoCategoria}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	/**
	 * @param codigo do produto que será removido
	 */
	public ResponseEntity<Produto> atualizar(@PathVariable(name = "codigoCategoria") Long codigoCategoria,
			@PathVariable(name = "codigo") Long codigo, @RequestBody Produto produto) {

		Produto produtoAtualizado = prodServico.atualizar(codigoCategoria, codigo, produto);
		return ResponseEntity.ok(produtoAtualizado);
	}

	@ApiOperation(value = "deletar")
	@DeleteMapping(path = "/{codigo}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	/**
	 * @param codigo do produto que será removido
	 */
	public void deletar(@PathVariable(name = "codigo") Long codigo) {
		LOGGER.trace("Iniciando a remoção do registro {} ", codigo);

		prodServico.remover(codigo);

		LOGGER.info("Iniciando a remoção do registro [{}] ", codigo);
	}

	@ApiOperation(value = "criar", nickname = "Salvar")
	@PostMapping()
	public ResponseEntity<Produto> criar(@Valid @RequestBody() Produto produto) {

		Produto produtoNovo = prodServico.criar(produto);
		LOGGER.trace("Produto Novo criado {} ", produtoNovo);

		return ResponseEntity.status(HttpStatus.CREATED).body(produtoNovo);
	}

}
