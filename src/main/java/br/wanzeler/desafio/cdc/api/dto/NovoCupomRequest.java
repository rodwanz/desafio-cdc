package br.wanzeler.desafio.cdc.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.wanzeler.desafio.cdc.domain.model.Cupom;
import br.wanzeler.desafio.cdc.valido.UniqueValue;

public class NovoCupomRequest {
	
	@NotBlank
	@UniqueValue(domainClass = Cupom.class, fieldName = "codigo")
	private String codigo;	
	@NotNull @Positive private BigDecimal percentualDesconto;
	private @NotNull @Future 
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING) LocalDate validade;
		
	public NovoCupomRequest(@NotBlank String codigo, 
			@NotBlank @Positive BigDecimal percentualDesconto) {
		super();
		this.codigo = codigo;
		this.percentualDesconto = percentualDesconto;
	}

	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}

	public Cupom naFormaDoCpom(EntityManager gerente) {
		return new Cupom(codigo, percentualDesconto, validade);
	}

	@Override
	public String toString() {
		return "NovoCupomRequest [codigo = " + codigo + ", percentualDesconto = " + percentualDesconto + ", validade = "
				+ validade + "]";
	}	
}
