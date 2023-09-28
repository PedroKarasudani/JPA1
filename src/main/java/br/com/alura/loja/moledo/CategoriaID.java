package br.com.alura.loja.moledo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CategoriaID implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private String tipo;
	
	public CategoriaID() {
	}
	
	
	public CategoriaID(String nome, String tipo) {
		this.nome = nome;
		this.tipo = tipo;
	}


	public String getNome() {
		return nome;
	}

	public String getTipo() {
		return tipo;
	}
	
	
}
