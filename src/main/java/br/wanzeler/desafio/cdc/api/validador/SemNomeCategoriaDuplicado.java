package br.wanzeler.desafio.cdc.api.validador;

import java.util.Optional;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.wanzeler.desafio.cdc.api.dto.CategoriaForma;
import br.wanzeler.desafio.cdc.domain.model.Categoria;
import br.wanzeler.desafio.cdc.domain.repositories.CategoriaRepository;

public class SemNomeCategoriaDuplicado implements Validator{
	
	private CategoriaRepository repositorioCategoria;
	
	public SemNomeCategoriaDuplicado(CategoriaRepository repositorioCategoria) {
		super();
		this.repositorioCategoria = repositorioCategoria;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return CategoriaForma.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CategoriaForma formaCategoria = (CategoriaForma) target;
		Optional<Categoria> possivelCategoria = repositorioCategoria.findByNome(formaCategoria.getNome());
		
		if (possivelCategoria.isPresent()) {
			errors.rejectValue("nome", null, "Já existe uma Categoria com este nome! " + formaCategoria.getNome());
		}	
	}
}
