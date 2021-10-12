package com.vendas.gestaovendas.controlador;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vendas.gestaovendas.entidades.Categoria;
import com.vendas.gestaovendas.servico.CategoriaServico;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController()
@RequestMapping("/categorias")
@Api(tags = "Categoria")
public class CategoriaControlador {

	@Autowired
	private CategoriaServico service;

	@ApiOperation(value = "Listar")
	@GetMapping
	public List<Categoria> listarTodas() {

		return service.listarTodas();
	}

	@ApiOperation(value = "buscarPorId")
	@GetMapping(path = "/{codigo}")
	/**
	 * @param codigo da categoria
	 * @return um objeto optional de categoria caso a resposta seja ok.
	 */
	public ResponseEntity<Optional<Categoria>> buscarPorId(@PathVariable(name = "codigo") Long codigo) {

		Optional<Categoria> optCategoria = service.buscarPorId(codigo);

		return optCategoria.isPresent() ? ResponseEntity.ok(optCategoria) : ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "atualizar")
	@PutMapping(path = "/{codigo}")
	/**
	 * @param codigo da categoria
	 * @return um objeto optional de categoria caso a resposta seja ok.
	 */
	public ResponseEntity<Categoria> atualizar(@PathVariable(name = "codigo") Long codigo,
			@RequestBody Categoria categoria) {

		Categoria categoriaAtualizada = service.atualizarCategoria(codigo, categoria);

		return ResponseEntity.ok(categoriaAtualizada);
	}

	@ApiOperation(value = "deletar")
	@DeleteMapping(path = "/{codigo}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	/**
	 * @param codigo da categoria
	 */
	public void deletar(@PathVariable(name = "codigo") Long codigo) {

		service.deleteById(codigo);
	}

	@ApiOperation(value = "criar")
	@PostMapping
	/**
	 * @param categoria
	 * @return um objeto optional de categoria, caso salvo com sucesso.
	 */
	public ResponseEntity<Categoria> criar(@Valid() @RequestBody() Categoria categoria) {

		Categoria categoriaSalva = service.salvar(categoria);

		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
}
