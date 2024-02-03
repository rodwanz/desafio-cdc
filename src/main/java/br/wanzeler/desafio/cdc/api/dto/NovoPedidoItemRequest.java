package br.wanzeler.desafio.cdc.api.dto;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.wanzeler.desafio.cdc.domain.model.ItemPedido;
import br.wanzeler.desafio.cdc.domain.model.Livro;
import br.wanzeler.desafio.cdc.valido.ExistsId;

public class NovoPedidoItemRequest {
	
	@NotNull
	@ExistsId(domainClass = Livro.class, fieldName = "id")
	private Long idLivro;
	@Positive
	private int quantidade;
	
	public NovoPedidoItemRequest(@NotNull Long idLivro, @Positive int quantidade) {
		super();
		this.idLivro = idLivro;
		this.quantidade = quantidade;
	}

	public Long getIdLivro() {
		return idLivro;
	}

	public ItemPedido naFormaDoItem(EntityManager manager) {
		@NotNull Livro livro = manager.find(Livro.class, idLivro);
		return new ItemPedido(livro, quantidade);
	}	
}
