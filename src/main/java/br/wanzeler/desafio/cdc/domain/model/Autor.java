package br.wanzeler.desafio.cdc.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank @Size(max = 50) String nome;
	private @NotBlank  @Size(max = 100) String email;
	private @NotBlank @Size(max = 100) String descricao; 
	
	
	@Deprecated
	public Autor() {}
	
	public Autor(@NotBlank @Size(max = 50) String nome, @NotBlank @Email @Size(max = 100) String email,
			@NotBlank @Size(max = 100) String descricao) {
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return "Autor [id=" + id + ", nome=" + nome + ", email=" + email + ", descricao=" + descricao + "]";
	}

	public String getNome() {
		return this.nome;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
