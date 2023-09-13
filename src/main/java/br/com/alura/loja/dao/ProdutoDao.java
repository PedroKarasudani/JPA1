package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.moledo.Produto;

public class ProdutoDao {
	
	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}
	
	public void returnManaged(Produto produto) {
		this.em.merge(produto);
	}
	
	public void remover(Produto produto) {
		this.em.remove(produto);
	}
}
