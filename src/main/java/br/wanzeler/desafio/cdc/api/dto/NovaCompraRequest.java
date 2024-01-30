package br.wanzeler.desafio.cdc.api.dto;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.wanzeler.desafio.cdc.domain.model.Compra;
import br.wanzeler.desafio.cdc.domain.model.Estado;
import br.wanzeler.desafio.cdc.domain.model.Pais;
import br.wanzeler.desafio.cdc.valido.Documento;
import br.wanzeler.desafio.cdc.valido.ExistsId;

public class NovaCompraRequest {
	
	@NotBlank
	private String email;
	@NotBlank
	private String nome;
	@NotBlank
	private String sobrenome;
	@NotBlank
	@Documento
	private String documento;
	@NotBlank
	private String endereco;
	@NotBlank
	private String complemento;
	@NotBlank
	private String cidade;
	@NotNull
	@ExistsId(domainClass = Pais.class, fieldName = "id")
	private Long idPais;
	@ExistsId(domainClass = Estado.class, fieldName = "id")
	private Long idEstado;
	@NotBlank
	private String telefone;
	@NotBlank
	private String cep;
	@Valid
	@NotNull
	private NovoPedidoRequest pedido;
	
	public NovaCompraRequest(@NotBlank String email, @NotBlank String nome, @NotBlank String sobrenome,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
			@NotBlank String cidade, @NotNull Long idPais, Long idEstado, @NotBlank String telefone,
			@NotBlank String cep, @Valid @NotNull NovoPedidoRequest pedido) {
		super();
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documento = documento;
		this.endereco = endereco;
		this.complemento = complemento;
		this.cidade = cidade;
		this.idPais = idPais;
		this.idEstado = idEstado;
		this.telefone = telefone;
		this.cep = cep;
		this.pedido = pedido;
	}
	
	public NovoPedidoRequest getPedido() {
		return pedido;
	}
	
	public String getDocumento() {
		return documento;
	}
	
	@Override
	public String toString() {
		return "NovaCompraRequest [email = " + email + ", nome = " + nome + ", sobrenome = " + sobrenome + ", documento = "
				+ documento + ", endereco = " + endereco + ", complemento = " + complemento + ", cidade = " + cidade
				+ ", idPais = " + idPais + ", idEstado = " + idEstado + ", telefone = " + telefone + ", cep = " + cep
				+ ", pedido = " + pedido + "]";
	}

	public Long getIdPais() {
		return idPais;
	}

	public Long getIdEStado() {
		return idEstado;
	}
	
	public Compra naFormaDaCompra(EntityManager manager) {
		@NotNull Pais pais = manager.find(Pais.class, idPais);
		Compra compra = new Compra(email, nome, documento, endereco, complemento, cidade, pais, telefone, cep);
		if(idEstado!=null) {
			compra.setEstado(manager.find(Estado.class, idEstado));
		}
		return compra; 
	}

	public boolean temEstado() {
		return idEstado != null;
	}
}
