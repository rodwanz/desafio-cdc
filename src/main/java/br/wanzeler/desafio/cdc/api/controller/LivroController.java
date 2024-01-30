package br.wanzeler.desafio.cdc.api.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.wanzeler.desafio.cdc.api.dto.LivroForma;
import br.wanzeler.desafio.cdc.domain.model.Livro;
import br.wanzeler.desafio.cdc.domain.repositories.LivroRepository;

@RestController
public class LivroController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private LivroRepository repositorioLivro;

	@PostMapping(value = "/livros")
	@Transactional
	public String criaLivro(@RequestBody @Valid LivroForma formandoLivro) {
		Livro novoLivro = formandoLivro.modeloDeAcordoLivro(manager);
		manager.persist(novoLivro);
		return novoLivro.toString();
	}

	@GetMapping(value = "/todos_livros")
	@Transactional
	public String todos() {
		return repositorioLivro.findAll().toString();
	}
}
