package br.wanzeler.desafio.cdc.api.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.wanzeler.desafio.cdc.api.dto.NovaCompraRequest;
import br.wanzeler.desafio.cdc.api.validador.EstadoPertenceAPaisValidado;
import br.wanzeler.desafio.cdc.domain.model.Compra;

@RestController
public class FinalizaCompraParte1Controller {
	
	@Autowired
	private EstadoPertenceAPaisValidado estadoPertenceAPaisValidado;
		
	@PersistenceContext
	private EntityManager manager;

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(estadoPertenceAPaisValidado);
	}

	@PostMapping(value = "/compras")
	public String cria(@RequestBody @Valid NovaCompraRequest request) {
		Compra novaCompra = request.naFormaDaCompra(manager);
		return novaCompra.toString();
	}

}
