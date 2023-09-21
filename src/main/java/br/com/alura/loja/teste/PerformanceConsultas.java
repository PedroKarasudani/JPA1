package br.com.alura.loja.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.moledo.Categoria;
import br.com.alura.loja.moledo.Cliente;
import br.com.alura.loja.moledo.ItemPedido;
import br.com.alura.loja.moledo.Pedido;
import br.com.alura.loja.moledo.Produto;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.valueobject.RelatorioDeVendasVo;

public class PerformanceConsultas {
	//Teste
	public static void main(String[] args) {
		//Inserindo dados no banco de dados
		popularBancoDeDados();
		//Iniciando JPA, para extrair os dados das tabelas
		EntityManager em = JPAUtil.getEntityManager();
		Pedido pedido = em.find(Pedido.class, 1l);
		em.close();
		System.out.println(pedido.getCliente().getNome());
	}
	
	
	
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria videoGames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");
		
		Produto celular = new Produto("Xiaomi Redmi", "128GB", new BigDecimal("800"), celulares);
		Produto videoGame = new Produto("PS5", "Digital", new BigDecimal("3000"), videoGames);
		Produto macbook = new Produto("Macbook", "Pro", new BigDecimal("11000"), informatica);
		
		Cliente cliente = new Cliente("Pedro Lourenco", "096096096");
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, celular, pedido));
		pedido.adicionarItem(new ItemPedido(40, videoGame, pedido));
		Pedido pedido2 = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(2, macbook, pedido2));
		
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		PedidoDao pedidoDao = new PedidoDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(videoGames);
		categoriaDao.cadastrar(informatica);
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(videoGame);
		produtoDao.cadastrar(macbook);
		clienteDao.cadastrar(cliente);
		pedidoDao.cadastrar(pedido);
		pedidoDao.cadastrar(pedido2);
		
		em.getTransaction().commit();
		
	}
}
