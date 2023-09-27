package br.com.alura.loja.teste;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.moledo.Categoria;
import br.com.alura.loja.moledo.Cliente;
import br.com.alura.loja.moledo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class TesteCriteria {
	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		produtoDao.buscarPorParametrosComCriteria("PS5", null, LocalDate.now());
	}
	
	
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria videoGames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");
		
		Produto celular = new Produto("Xiaomi Redmi", "128GB", new BigDecimal("800"), celulares);
		Produto videoGame = new Produto("PS5", "Digital", new BigDecimal("3000"), videoGames);
		Produto macbook = new Produto("Macbook", "Pro", new BigDecimal("11000"), informatica);
		
		Cliente cliente = new Cliente("Pedro Lourenco", "096096096");
		
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao  clienteDao = new ClienteDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(videoGames);
		categoriaDao.cadastrar(informatica);
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(videoGame);
		produtoDao.cadastrar(macbook);
		clienteDao.cadastrar(cliente);
		
		em.getTransaction().commit();
	}	
}
