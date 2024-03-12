package br.wanzeler.desafio.cdc.api.dto;

import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.StringUtils;

import br.wanzeler.desafio.cdc.domain.model.Compra;
import br.wanzeler.desafio.cdc.domain.model.Cupom;
import br.wanzeler.desafio.cdc.domain.model.Estado;
import br.wanzeler.desafio.cdc.domain.model.Pais;
import br.wanzeler.desafio.cdc.domain.model.Pedido;
import br.wanzeler.desafio.cdc.domain.repositories.CupomRepository;
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
	//@ExistsId(domainClass = Cupom.class,fieldName = "codigo")
	private String codigoCupom;
	
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
	
	public void setCupom(String codigoCupom) {
		 this.codigoCupom = codigoCupom;
	}
	
	public NovoPedidoRequest getPedido() {
		return pedido;
	}
	
	public String getDocumento() {
		return documento;
	}

	public Long getIdPais() {
		return idPais;
	}

	public Long getIdEStado() {
		return idEstado;
	}
	
	public Compra naFormaDaCompra(EntityManager manager, CupomRepository cupomRepository) {
		@NotNull Pais pais = manager.find(Pais.class, idPais);
		Function<Compra, Pedido> funcaoCriacaoPedido = pedido.naFormaDaCompra(manager);
		Compra compra = new Compra(email, nome, documento, endereco, 
				complemento, cidade, pais, telefone, cep, funcaoCriacaoPedido);
		if(idEstado!=null) {
			compra.setEstado(manager.find(Estado.class, idEstado));
		}
		if(StringUtils.hasText(codigoCupom)) {
			Cupom cupom = cupomRepository.getByCodigo(codigoCupom);
			compra.aplicaCupom(cupom);
		}
		
		return compra; 
	}

	public boolean temEstado() {
		return idEstado != null;
	}

	public Optional<String> getCodigoCupom() {
		return Optional.ofNullable(codigoCupom);
	}
}
