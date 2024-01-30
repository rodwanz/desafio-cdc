package br.wanzeler.desafio.cdc.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.wanzeler.desafio.cdc.domain.model.Pais;

public interface CadastroPaisRepositorio extends JpaRepository<Pais, Long>{

	//public void save(CadastroPais novoPais);

	public Optional<Pais> findByNome(String nome);

}
