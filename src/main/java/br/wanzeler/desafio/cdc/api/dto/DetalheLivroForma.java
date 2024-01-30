package br.wanzeler.desafio.cdc.api.dto;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

import br.wanzeler.desafio.cdc.domain.model.Livro;

public class DetalheLivroForma {
	
	private DetalheAutorForma autor;
	private String titulo;
	private String isbn;
	private int numeroPaginas;
	private BigDecimal preco;
	private String resumo;
	private String sumario;
	private String dataPublicacao;
	
	public DetalheLivroForma(Livro livro) {
		titulo = livro.getTitulo();
		autor = new DetalheAutorForma(livro.getAutor());
		isbn = livro.getIsbn();
		numeroPaginas = livro.getNumeroPaginas();
		preco = livro.getPreco();
		resumo = livro.getResumo();
		sumario = livro.getSumario();
		dataPublicacao = livro.getDataPublicacao()
				.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));;
	}

	public DetalheAutorForma getAutor() {
		return autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public String getResumo() {
		return resumo;
	}

	public String getSumario() {
		return sumario;
	}

	public String getDataPublicacao() {
		return dataPublicacao;
	}
}

