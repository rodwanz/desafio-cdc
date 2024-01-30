package br.wanzeler.desafio.cdc.domain.model;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

public class Compra {
	
	private @NotBlank @Email String email;
	private @NotBlank String nome;
	private @NotBlank String sobrenome;
	private @NotBlank String documento;
	private @NotBlank String endereco;
	private @NotBlank String complemento;
	@ManyToOne
	private @NotNull Pais pais;
	private @NotBlank String telefone;
	private @NotBlank String cep;
	@ManyToOne
	private Estado estado;
	
	public Compra(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobrenome,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento, @NotNull Pais pais,
			@NotBlank String telefone, @NotBlank String cep) {
		super();
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documento = documento;
		this.endereco = endereco;
		this.complemento = complemento;
		this.pais = pais;
		this.telefone = telefone;
		this.cep = cep;
	}
	
	

	@Override
	public String toString() {
		return "Compra [email = " + email + ", nome = " + nome + ", sobrenome = " + sobrenome + ", documento = " + documento
				+ ", endereco = " + endereco + ", complemento = " + complemento + ", pais = " + pais + ", telefone = "
				+ telefone + ", cep = " + cep + ", estado = " + estado + "]";
	}



	public void setEstado(@NotNull @Valid Estado estado) {
		Assert.notNull(pais, "Não associar um estado enquanto o país for nulo");
		Assert.isTrue(estado.pertenceAPais(pais), "Este estado não pertence ao país associado");
		this.estado = estado;
	}
}