package br.com.alura.loja.moledo;

import javax.persistence.Entity;

@Entity
public class Informatica extends Produto {
	private String marca;
	private String modelo;

	public Informatica(String marca, String modelo) {
		this.marca = marca;
		this.modelo = modelo;
	}

	public Informatica() {

	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

}
