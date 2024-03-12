package br.wanzeler.desafio.cdc.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Cupom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String codigo;
	private @NotNull @Positive BigDecimal percentualDesconto;
	private @NotNull @Future LocalDate validade;
	
	@Deprecated
	public Cupom() {}

	public Cupom(@NotBlank String codigo, @NotNull @Positive BigDecimal percentualDesconto,
			@NotNull @Future LocalDate validade) {
		this.codigo = codigo;
		this.percentualDesconto = percentualDesconto;
		this.validade = validade;
	}

	public boolean valido() {
		return LocalDate.now().compareTo(this.validade) <= 0;
	}

	public BigDecimal getPercentualDesconto() {
		return percentualDesconto;
	}
	
	public LocalDate getValidade() {
		return validade;
	}

	@Override
	public String toString() {
		return "Cupom [codigo = " + codigo + ", percentualDesconto = " + percentualDesconto + ", validade = " + validade
				+ "]";
	}	
}
