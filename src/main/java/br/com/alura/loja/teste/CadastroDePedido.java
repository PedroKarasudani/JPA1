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

public class CadastroDePedido {
	//Teste
	public static void main(String[] args) {
		//Inserindo dados no banco de dados
		popularBancoDeDados();
		//Iniciando JPA, para extrair os dados das tabelas
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		//Procurando os dados pelo id
		Produto produto = produtoDao.buscarPorId(1l);
		Produto produto2 = produtoDao.buscarPorId(2l);
		Produto produto3 = produtoDao.buscarPorId(3l);
		Cliente cliente = clienteDao.buscarPorId(1l);

		//criando pedido e adicionando item
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, produto, pedido));
		pedido.adicionarItem(new ItemPedido(40, produto2, pedido));
		
		Pedido pedido2 = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(2, produto3, pedido2));
		
		//adionando no banco de dados
		em.getTransaction().begin();
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);
		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		System.out.println("Valor Total: " + totalVendido);
		
		List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioDeVendas();
		relatorio.forEach(System.out::println);
		em.close();
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
		ClienteDao clienteDao = new ClienteDao(em);
		
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
