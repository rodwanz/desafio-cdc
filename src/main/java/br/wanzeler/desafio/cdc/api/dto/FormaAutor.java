package br.wanzeler.desafio.cdc.api.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.wanzeler.desafio.cdc.domain.model.Autor;
import br.wanzeler.desafio.cdc.valido.UniqueValue;


public class FormaAutor {
	
	@NotBlank
	@Size(max = 255)
	@Column(name = "nome")
	private String nome;
	
	@NotBlank
	@Email
	@UniqueValue(domainClass = Autor.class, fieldName = "email")
	@Size(max = 255)
	@Column(name = "email")
	private String email;
	
	@NotBlank
	@Size(max = 255)
	@Column(name = "descricao")
	private String descricao;
	
	public FormaAutor(@NotBlank @Size(max = 255) String nome, @NotBlank @Email @Size(max = 255) String email,
			@NotBlank @Size(max = 255) String descricao) {
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
	}

	public Autor modelandoComoAForma() {
		return new Autor(this.nome, this.email, this.descricao);
	}
	
	public String getEmail() {
		return email;		
	}
}

