package br.com.alura.loja.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.moledo.Categoria;
import br.com.alura.loja.moledo.CategoriaID;
import br.com.alura.loja.moledo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	
	public static void main (String[] args) {
		cadastrarProduto();
		mostrarInformacao();
	}

	private static void mostrarInformacao() {
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		Produto p1 = produtoDao.buscarPorId(1l);
		System.out.println(p1.getPreco());
		
		List<Produto> p2 = produtoDao.buscarTodos();
		p2.forEach(v -> System.out.println(v.getNome()));
		
		List<Produto> p3 = produtoDao.buscarPeloNome("Xiaomi Redmi");
		p3.forEach(v -> System.out.println(v.getNome()));
		
		List<Produto> p4 = produtoDao.buscarPeloNomeDaCategoria("CELULARES");
		p4.forEach(v -> System.out.println(v.getNome()));
		
		BigDecimal p5 = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
		System.out.println(p5);
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);		
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		
		em.getTransaction().commit();
		
		em.find(Categoria.class, new CategoriaID("CELULARES", "xpto"));
		
		em.close();
	}
}
