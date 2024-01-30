package br.wanzeler.desafio.cdc.api.dto;

import javax.validation.constraints.NotBlank;

import br.wanzeler.desafio.cdc.domain.model.Categoria;
import br.wanzeler.desafio.cdc.valido.UniqueValue;

public class CategoriaForma {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private  String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Categoria deAcordoCategoria() {
		return new Categoria(nome);
	}
}
