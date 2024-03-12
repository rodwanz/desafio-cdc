package br.wanzeler.desafio.cdc.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.wanzeler.desafio.cdc.domain.model.Cupom;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long>{
	
	public Cupom getByCodigo(String codigo);

}
