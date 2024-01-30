package br.wanzeler.desafio.cdc.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
//import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

//import br.wanzeler.desafio.cdc.api.controller.Livro;
import br.wanzeler.desafio.cdc.domain.model.Autor;
import br.wanzeler.desafio.cdc.domain.model.Categoria;
import br.wanzeler.desafio.cdc.domain.model.Livro;
import br.wanzeler.desafio.cdc.valido.ExistsId;
import br.wanzeler.desafio.cdc.valido.UniqueValue;

public class LivroForma {

	@NotBlank
	@UniqueValue(domainClass = Livro.class, fieldName = "titulo")
	private String titulo;

	@NotBlank
	@Size(max = 500)
	private String resumo;

	@NotBlank
	private String sumario;

	@NotNull
	@Min(20)
	private BigDecimal preco;

	@Min(100)
	private int numeroPaginas;

	@NotBlank
	@UniqueValue(domainClass = Livro.class, fieldName = "isbn")
	private String isbn;

	@Future
	@NotNull
	// @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate dataPublicacao;

	@NotNull
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoria;

	@NotNull
	@ExistsId(domainClass = Autor.class, fieldName = "id")
	private Long idAutor;
	
	//@Deprecated
	//public LivroForma() {}

	public LivroForma(String titulo, String resumo, String sumario, BigDecimal preco, int numeroPaginas, String isbn,
			@Future @NotNull LocalDate dataPublicacao, Long idCategoria, Long idAutor) {
		super();
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.preco = preco;
		this.numeroPaginas = numeroPaginas;
		this.isbn = isbn;
		this.dataPublicacao = dataPublicacao;
		this.idCategoria = idCategoria;
		this.idAutor = idAutor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public String getSumario() {
		return sumario;
	}

	public void setSumario(String sumario) {
		this.sumario = sumario;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Long getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(Long idAutor) {
		this.idAutor = idAutor;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public Livro modeloDeAcordoLivro(EntityManager manager) {

		@NotNull
		Autor autor = manager.find(Autor.class, idAutor);

		@NotNull
		Categoria categoria = manager.find(Categoria.class, idCategoria);

		Assert.state(Objects.nonNull(autor),
				"Você esta querendo cadastrar um livro para um autor que nao existe no banco " + idAutor);

		Assert.state(Objects.nonNull(categoria),
				"Você esta querendo cadastrar um livro para uma categoria que nao existe no banco " + idCategoria);
		return new Livro(titulo, resumo, sumario, preco, numeroPaginas, isbn, dataPublicacao, autor, categoria);

	}
}
