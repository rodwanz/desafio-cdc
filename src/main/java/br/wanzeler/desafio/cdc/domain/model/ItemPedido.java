package br.wanzeler.desafio.cdc.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;
@Embeddable
@Data
public class ItemPedido {
	
	@ManyToOne
	private @NotNull Livro livro;
	private @Positive int quantidade;	
	private @Positive BigDecimal precoMomento;
	
	
	@Deprecated
	public ItemPedido() {}

	public ItemPedido(@NotNull Livro livro, @Positive int quantidade) {
		super();
		this.livro = livro;
		this.quantidade = quantidade;
		this.precoMomento = livro.getPreco();
	}
	
	public BigDecimal total() {
		return precoMomento.multiply(new BigDecimal(quantidade));
	}
}
