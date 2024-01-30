package br.wanzeler.desafio.cdc.api.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.wanzeler.desafio.cdc.api.dto.FormaAutor;
import br.wanzeler.desafio.cdc.api.validador.SemEmailAutorDuplicadoValidator;
import br.wanzeler.desafio.cdc.domain.model.Autor;
import br.wanzeler.desafio.cdc.domain.repositories.AutorRepository;

@RestController
public class NovoAutorController {
	
	@Autowired
	private AutorRepository repositorioAutor;
	
	@InitBinder
	public void init(WebDataBinder dataBinder) {
		dataBinder.addValidators(new SemEmailAutorDuplicadoValidator(repositorioAutor));
	}
	
	@PostMapping(value = "/novos_autores")
	@Transactional
	public void novo(@RequestBody @Valid FormaAutor forma) {
		Autor novissimoAutor = forma.modelandoComoAForma();
		repositorioAutor.save(novissimoAutor);
	}
}
