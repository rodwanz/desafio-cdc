package br.wanzeler.desafio.cdc.domain.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotNull @Valid @OneToOne Compra compra;
	private @ElementCollection @Size(min = 1) Set<ItemPedido> itens = new HashSet<>();

	public Pedido(@NotNull @Valid Compra compra, @Size(min = 1) Set<ItemPedido> itens) {
		Assert.isTrue(!itens.isEmpty(), "todo pedido tem que ter pelo menos um item");
		this.compra = compra;
		this.itens.addAll(itens);
	}

	public boolean totalIgual(@Positive @NotNull BigDecimal total) {
		BigDecimal totalPedido = itens
				.stream()
				.map(ItemPedido::total).reduce(BigDecimal.ZERO,
				(atual, proximo) -> atual.add(proximo));

		return totalPedido.doubleValue() == total.doubleValue();
	}
}
