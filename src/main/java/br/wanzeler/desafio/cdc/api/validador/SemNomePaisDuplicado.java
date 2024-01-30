package br.wanzeler.desafio.cdc.api.validador;

import java.util.Optional;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.wanzeler.desafio.cdc.api.dto.CadastroPais;
import br.wanzeler.desafio.cdc.domain.model.Pais;
import br.wanzeler.desafio.cdc.domain.repositories.CadastroPaisRepositorio;

public class SemNomePaisDuplicado implements Validator{
	
	private CadastroPaisRepositorio repositorioPais;
	
	public SemNomePaisDuplicado(CadastroPaisRepositorio repositorioPais) {
		super();
		this.repositorioPais = repositorioPais;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return CadastroPais.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CadastroPais formandoPais = (CadastroPais) target;
		Optional<Pais> possivelPais = repositorioPais.findByNome(formandoPais.getNome());
		
		if (possivelPais.isPresent()) {
			errors.rejectValue("nome", null, "Já existe um país com este nome! " + formandoPais.getNome());
		}	
	}
}
