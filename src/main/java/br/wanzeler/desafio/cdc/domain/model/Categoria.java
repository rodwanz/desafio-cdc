package br.wanzeler.desafio.cdc.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 255)
	@Column(unique = false)
	private String nome;
	
	@Deprecated
	public Categoria() {

	}
	
	public Categoria(String nome) {
		super();
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Categoria [id = " + id + ", nome = " + nome + "]";
	}	
}
