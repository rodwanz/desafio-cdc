package br.wanzeler.desafio.cdc.api.dto;

import br.wanzeler.desafio.cdc.domain.model.Autor;

public class DetalheAutorForma {
	
	private String nome;
	private String descricao;
	
	public DetalheAutorForma(Autor autor) {
		nome = autor.getNome();
		descricao = autor.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
}

