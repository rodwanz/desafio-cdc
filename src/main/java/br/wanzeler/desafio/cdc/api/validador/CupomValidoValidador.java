package br.wanzeler.desafio.cdc.api.validador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.wanzeler.desafio.cdc.api.dto.NovaCompraRequest;
import br.wanzeler.desafio.cdc.domain.model.Cupom;
import br.wanzeler.desafio.cdc.domain.repositories.CupomRepository;

@Component
public class CupomValidoValidador implements Validator{
	
	@Autowired
	private CupomRepository cupomRepository;
	
	public CupomValidoValidador() {
		super();
		this.cupomRepository = cupomRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return NovaCompraRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}
		
		NovaCompraRequest solicitando = (NovaCompraRequest) target;
		Optional<String> possivelCodigo = solicitando.getCodigoCupom();
		if(possivelCodigo.isPresent()) {
			Cupom cupom = cupomRepository.getByCodigo(possivelCodigo.get());
			if(!cupom.valido()) {
				errors.rejectValue("codigoCupom", null, "Este cupom não é mais válido!");
			}
		}		
	}
}
