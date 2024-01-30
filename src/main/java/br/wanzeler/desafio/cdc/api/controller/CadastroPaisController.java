package br.wanzeler.desafio.cdc.api.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.wanzeler.desafio.cdc.api.dto.CadastroPais;
import br.wanzeler.desafio.cdc.domain.model.Pais;

@RestController
public class CadastroPaisController {
		
	//@Autowired
	//private CadastroPaisRepositorio repositorioPais;
	
	@PersistenceContext
	private EntityManager gerente;
		
	@PostMapping(value = "/novo_pais")
	@Transactional
	public String novo(@RequestBody @Valid CadastroPais formandoPais) {
		//Pais novoPais = new Pais(formandoPais.getNome());
		Pais novoPais = formandoPais.formaDeAcordoPais(gerente);
		gerente.persist(novoPais);
		return novoPais.toString();
		//repositorioPais.save(novoPais);
	}
}
