package br.com.alura.loja.moledo;

import javax.persistence.Entity;

@Entity
public class Livro extends Produto {
	private String autor;
	private Integer numeroDePaginas;

	public Livro(String autor, Integer numeroDePaginas) {
		this.autor = autor;
		this.numeroDePaginas = numeroDePaginas;
	}

	public Livro() {

	}

	public String getAutor() {
		return autor;
	}

	public Integer getNumeroDePaginas() {
		return numeroDePaginas;
	}

}
