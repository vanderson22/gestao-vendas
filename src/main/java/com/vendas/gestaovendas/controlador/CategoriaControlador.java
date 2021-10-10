package com.vendas.gestaovendas.controlador;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendas.gestaovendas.entidades.Categoria;
import com.vendas.gestaovendas.servico.CategoriaServico;

@RestController()
@RequestMapping("/categorias")
public class CategoriaControlador {

	@Autowired
	private CategoriaServico service;

	@GetMapping
	public List<Categoria> listarTodas() {

		return service.listarTodas();
	}

	@GetMapping(path = "/{codigo}")
	/**
	 * @param codigo da categoria
	 * @return um objeto optional de categoria caso a resposta seja ok.
	 */
	public ResponseEntity<Optional<Categoria>> buscarPorId(@PathVariable(name = "codigo") Long codigo) {

		Optional<Categoria> optCategoria = service.buscarPorId(codigo);

		return optCategoria.isPresent() ? ResponseEntity.ok(optCategoria) : ResponseEntity.notFound().build();
	}

	@PutMapping(path = "/{codigo}")
	/**
	 * @param codigo da categoria
	 * @return um objeto optional de categoria caso a resposta seja ok.
	 */
	public ResponseEntity<Categoria> atualizarCategoria(@PathVariable(name = "codigo") Long codigo,
			@RequestBody Categoria categoria) {

		Categoria categoriaAtualizada = service.atualizarCategoria(codigo, categoria);

		return ResponseEntity.ok(categoriaAtualizada);
	}

	@PostMapping
	/**
	 * @param categoria
	 * @return um objeto optional de categoria, caso salvo com sucesso.
	 */
	public ResponseEntity<Categoria> buscarPorId(@Valid() @RequestBody() Categoria categoria) {

		Categoria categoriaSalva = service.salvar(categoria);

		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
}
