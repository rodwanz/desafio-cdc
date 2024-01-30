package br.wanzeler.desafio.cdc.api.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.wanzeler.desafio.cdc.api.dto.CadastroEstado;
import br.wanzeler.desafio.cdc.domain.model.Estado;

@RestController
public class CadastroEstadoController {
	
	@PersistenceContext
	private EntityManager gerente;
	
	@PostMapping(value = "/novo_estado")
	@Transactional
	public String novo(@RequestBody @Valid CadastroEstado formandoEstado) {
		Estado novoEstado = formandoEstado.estadoDeAcordoForma(gerente);
		gerente.persist(novoEstado);
		return novoEstado.toString();
	}

}
