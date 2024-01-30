package br.wanzeler.desafio.cdc.api.dto;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.wanzeler.desafio.cdc.domain.model.Estado;
import br.wanzeler.desafio.cdc.domain.model.Pais;
import br.wanzeler.desafio.cdc.valido.ExistsId;
import br.wanzeler.desafio.cdc.valido.UniqueValue;

public class CadastroEstado {
	
	@NotBlank
	@UniqueValue(domainClass = Estado.class, fieldName = "nome")
	private String nome;	
	@NotNull
	@ExistsId(domainClass = Pais.class, fieldName = "id")
	private Long idPais;
		
	public CadastroEstado(@NotBlank String nome,@NotNull Long idPais) {
		super();
		this.nome = nome;
		this.idPais = idPais;
	}
		
	@Override
	public String toString() {
		return "CadastroEstado [nome = " + nome + ", idPais = " + idPais + "]";
	}

	public Estado estadoDeAcordoForma(EntityManager gerente) {
		return new Estado(nome,gerente.find(Pais.class, idPais));
	}
}
