package br.wanzeler.desafio.cdc.api.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.wanzeler.desafio.cdc.api.dto.NovaCompraRequest;
import br.wanzeler.desafio.cdc.api.validador.CupomValidoValidador;
import br.wanzeler.desafio.cdc.api.validador.EstadoPertenceAPaisValidado;
import br.wanzeler.desafio.cdc.domain.model.Compra;
import br.wanzeler.desafio.cdc.domain.repositories.CupomRepository;

@RestController
public class FinalizaCompraParte1Controller {
	
	@Autowired
	private EstadoPertenceAPaisValidado estadoPertenceAPaisValidado;
	
	private CupomValidoValidador cupomValidoValidador;
		
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private CupomRepository cupomRepository;

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(estadoPertenceAPaisValidado, cupomValidoValidador);
	}

	@PostMapping(value = "/compras")
	@Transactional
	public String cria(@RequestBody @Valid NovaCompraRequest request) {
		Compra novaCompra = request.naFormaDaCompra(manager, cupomRepository);
		manager.persist(novaCompra);
		return novaCompra.toString();
	}

}
