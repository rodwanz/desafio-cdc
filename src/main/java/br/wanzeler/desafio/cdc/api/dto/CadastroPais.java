package br.wanzeler.desafio.cdc.api.dto;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import br.wanzeler.desafio.cdc.domain.model.Pais;
import br.wanzeler.desafio.cdc.valido.UniqueValue;

public class CadastroPais {
	
	@NotBlank
	@UniqueValue(domainClass = Pais.class, fieldName = "nome")
	private String nome;
			
	public CadastroPais() {
		super();
	}

	public CadastroPais(@NotBlank String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "CadastroPais [nome=" + nome + "]";
	}
		
	public Pais formaDeAcordoPais(EntityManager gerente) {
		return new Pais(nome);
	}
}
