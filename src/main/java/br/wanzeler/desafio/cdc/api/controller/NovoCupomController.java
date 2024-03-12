package br.wanzeler.desafio.cdc.api.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.wanzeler.desafio.cdc.api.dto.NovoCupomRequest;
import br.wanzeler.desafio.cdc.domain.model.Cupom;

@RestController
public class NovoCupomController {
	
	@PersistenceContext
	private EntityManager gerente;
	
	@PostMapping(value = "/cupons")
	@Transactional
	public String cria(@RequestBody @Valid NovoCupomRequest solicitando) {
		Cupom novoCuupom = solicitando.naFormaDoCpom(gerente);
		gerente.persist(novoCuupom);
		return novoCuupom.toString();
	}

}
