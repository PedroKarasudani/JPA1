package br.com.alura.loja.dao;

import java.util.List;

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
	
	public Produto buscarPorId(Long id) {
		return this.em.find(Produto.class, id);
	}
	
	public List<Produto> buscarTodos(){
		String SQTL = "SELECT p FROM Produto p";
		return this.em.createQuery(SQTL, Produto.class).getResultList();
	}
}
