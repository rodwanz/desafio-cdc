package br.wanzeler.desafio.cdc.api.validador;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.wanzeler.desafio.cdc.api.dto.NovaCompraRequest;
import br.wanzeler.desafio.cdc.domain.model.Estado;
import br.wanzeler.desafio.cdc.domain.model.Pais;

@Component
public class EstadoPertenceAPaisValidado implements Validator {

	@PersistenceContext
	private EntityManager gerente;

	@Override
	public boolean supports(Class<?> clazz) {
		return NovaCompraRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}

		NovaCompraRequest request = (NovaCompraRequest) target;
		if(request.temEstado()) {
			Pais pais = gerente.find(Pais.class, request.getIdPais());
			Estado estado = gerente.find(Estado.class, request.getIdEStado());
	
			if (!estado.pertenceAPais(pais)) {
				errors.rejectValue("idEstado", null, "Este estado não pertence a esse país");
			}
		}
	}
}
