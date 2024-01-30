package br.wanzeler.desafio.cdc.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.wanzeler.desafio.cdc.domain.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{

	Optional<Autor> findByEmail(String email);
}
