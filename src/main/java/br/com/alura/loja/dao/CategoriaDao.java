package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.moledo.Categoria;

public class CategoriaDao {
	
	private EntityManager em;

	public CategoriaDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Categoria categoria) {
		em.persist(categoria);
	}

	public void returnManaged(Categoria categoria) {
		this.em.merge(categoria);
	}
	
	public void remover(Categoria categoria) {
		this.em.remove(categoria);
	}
}
