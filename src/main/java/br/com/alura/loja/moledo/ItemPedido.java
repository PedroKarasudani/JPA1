package br.com.alura.loja.moledo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal precoUnitario;
	private int quantidade;
	
	@ManyToOne
	private Produto produto;
	
	@ManyToOne
	private Pedido pedido;
	
	public ItemPedido() {
	}

	public ItemPedido(int quantidade, Produto produto, Pedido pedido) {
		this.quantidade = quantidade;
		this.produto = produto;
		this.pedido = pedido;
		this.precoUnitario = produto.getPreco();
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public Pedido getPedido() {
		return pedido;
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
}
