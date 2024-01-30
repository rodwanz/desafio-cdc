package br.wanzeler.desafio.cdc.api.validador;

import java.util.Optional;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.wanzeler.desafio.cdc.api.dto.FormaAutor;
import br.wanzeler.desafio.cdc.domain.model.Autor;
import br.wanzeler.desafio.cdc.domain.repositories.AutorRepository;

public class SemEmailAutorDuplicadoValidator implements Validator {

	private AutorRepository repositorioAutor;

	public SemEmailAutorDuplicadoValidator(AutorRepository repositorioAutor) {
		this.repositorioAutor = repositorioAutor;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FormaAutor.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		FormaAutor forma = (FormaAutor) target;
		Optional<Autor> possivelAutor = repositorioAutor.findByEmail(forma.getEmail());

		if (possivelAutor.isPresent()) {
			errors.rejectValue("email", null, "Já existe um Autor com o mesmo email " + forma.getEmail());
		}
	}
}
