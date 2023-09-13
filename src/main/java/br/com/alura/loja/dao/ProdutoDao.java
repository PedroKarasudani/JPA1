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
		String jpql = "SELECT p FROM Produto p";
		return this.em.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> buscarPeloNome(String nome){
		String jpql = "SELECT p FROM Produto p WHERE p.nome = ?1";
		return this.em.createQuery(jpql, Produto.class).setParameter(1, nome).getResultList();
	}
	
	public List<Produto> buscarPeloNomeDaCategoria(String nome){
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
		return this.em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();
	}
	
}
