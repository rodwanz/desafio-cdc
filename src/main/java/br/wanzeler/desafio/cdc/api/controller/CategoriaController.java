package br.wanzeler.desafio.cdc.api.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.wanzeler.desafio.cdc.api.dto.CategoriaForma;
import br.wanzeler.desafio.cdc.api.validador.SemNomeCategoriaDuplicado;
import br.wanzeler.desafio.cdc.domain.model.Categoria;
import br.wanzeler.desafio.cdc.domain.repositories.CategoriaRepository;

@RestController
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repositorioCategoria;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(new SemNomeCategoriaDuplicado(repositorioCategoria));
	}
	
	@PostMapping(value = "/nova_categoria")
	@Transactional
	public void cria(@RequestBody @Valid CategoriaForma formandoCategoria) {
		Categoria novaCategoria = new Categoria (formandoCategoria.getNome());
		repositorioCategoria.save(novaCategoria);
	}
}
