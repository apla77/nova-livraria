package br.edu.ifrn.livraria.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class ItemPedido implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	private int quantidade = 1;
	
	private double valorTotal;
	
	@NotBlank(message = "Cep é uma informação obrigatória.")
	private String  cep;
	
	private String preco;
	private String prazo;
	
	@ManyToMany
	@JoinTable(name="itemPedidoLivro")
	public List<Livro> livro;
	
	@ManyToOne
	public Pedido pedido;

	@OneToOne
	@JoinColumn(name="frete_id")
	public Frete frete;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public List<Livro> getLivro() {
		return livro;
	}

	public void setLivro(List<Livro> livro) {
		this.livro = livro;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Frete getFrete() {
		return frete;
	}

	public void setFrete(Frete frete) {
		this.frete = frete;
	}

	
	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getPrazo() {
		return prazo;
	}

	public void setPrazo(String prazo) {
		this.prazo = prazo;
	}
 
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Quantidade: " + quantidade + "\nValorTotal: " + valorTotal + "\nCEP: " + cep
				+ "\nFrete: " + preco + "\nPrazo: " + prazo + "\nLivros" + livro.toString();
	}
	

}
