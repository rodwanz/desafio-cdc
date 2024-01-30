package br.wanzeler.desafio.cdc.api.controller;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.wanzeler.desafio.cdc.api.dto.DetalheLivroForma;
import br.wanzeler.desafio.cdc.domain.model.Livro;

@RestController
public class DetalheProduto {
	
	@PersistenceContext
	private EntityManager manager;
		
		@GetMapping(value = "/produtos/{id}")
		public DetalheLivroForma detalhe(@PathVariable ("id") Long id){	
			Livro livroEncontrado = Optional.ofNullable(manager.find(Livro.class, id))
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
			DetalheLivroForma detalheLivroForma = new DetalheLivroForma(livroEncontrado);
			
			return detalheLivroForma;
		}
}
