package br.wanzeler.desafio.cdc.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.wanzeler.desafio.cdc.domain.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
