package br.wanzeler.desafio.cdc.api.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import br.wanzeler.desafio.cdc.domain.model.Compra;
import br.wanzeler.desafio.cdc.domain.model.ItemPedido;
import br.wanzeler.desafio.cdc.domain.model.Pedido;

public class NovoPedidoRequest {

	@Positive
	@NotNull
	private BigDecimal total;
	@Size(min = 1)
	@Valid
	private List<NovoPedidoItemRequest> itens = new ArrayList<>();

	public NovoPedidoRequest(@Positive @NotNull BigDecimal total,
			@Size(min = 1) @Valid List<NovoPedidoItemRequest> itens) {
		super();
		this.total = total;
		this.itens = itens;
	}

	public List<NovoPedidoItemRequest> getItens() {
		return itens;
	}

	@Override
	public String toString() {
		return "NovoPedidoRequest [total = " + total + ", itens = " + itens + "]";
	}

	public Function<Compra, Pedido> naFormaDaCompra(EntityManager manager) {
		Set<ItemPedido> itensCalculados = itens
				.stream().map(item -> item.naFormaDoItem(manager))
				.collect(Collectors.toSet());
		return (compra) -> {
		Pedido pedido = new Pedido(compra,itensCalculados);
		Assert.isTrue(!pedido.totalIgual(total), "Total enviado não corresponde ao total real");
		return pedido;
		};
	}
}
