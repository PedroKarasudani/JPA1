package br.com.alura.loja.teste;

import java.math.BigDecimal;

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
		Cliente cliente = clienteDao.buscarPorId(1l);

		//criando pedido e adicionando item
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, produto, pedido));
		
		
		//adionando no banco de dados
		em.getTransaction().begin();
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);
		em.getTransaction().commit();
		em.close();
	}
	
	
	
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
		Cliente cliente = new Cliente("Pedro Lourenco", "096096096");
		
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		clienteDao.cadastrar(cliente);
		
		em.getTransaction().commit();
		
	}
}
