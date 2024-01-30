package br.wanzeler.desafio.cdc.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.wanzeler.desafio.cdc.domain.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	Optional<Categoria> findByNome(String nome);

}
