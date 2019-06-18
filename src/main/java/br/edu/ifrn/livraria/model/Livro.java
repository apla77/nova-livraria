package br.edu.ifrn.livraria.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;


@Entity
public class Livro implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Lob
	private byte[] imagemLivro;
	
	@Column(nullable = false, length = 100)
	@NotBlank(message = "Titulo é uma informação obrigatória.")
	private String titulo;
	
	@Column(nullable = false, length = 180)
	@NotBlank(message = "Sinopse é uma informação obrigatória.")
	private String sinopse;
	
	@Column(nullable = false, length = 20)
	@NotBlank(message = "Edição é uma informação obrigatória.")
	private String edicao;
	
	@Column(nullable = false, length = 4)
	@NotBlank(message = "Ano é uma informação obrigatória!")
	private String ano;
	
	@Column(nullable = false, length = 30)
	@NotBlank(message = "ISBN é uma informação obrigatória!")
	private String isbn;
	
	//@Column(nullable = false, length = 30)
	//@NotBlank(message = "Pesso é uma informação obrigatória!")
	private double peso;
	
	//@Column(nullable = false, length = 30)
	//@NotBlank(message = "Preço é uma informação obrigatória!")
	private String preco;
	
	@ManyToOne
	@JoinColumn(name = "editora_id_fk")
	public Editora editora;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id_fk")
	public Categoria categoria;
	
	@ManyToMany
	@JoinTable(name="livro_autor")
	public List<Autor> autor;
	
	@ManyToOne
	@JoinColumn(name = "pedido_id_fk")
	private Pedido pedido;
	
	@ManyToMany(mappedBy="livro")
	public List<ItemPedido> itemPedido;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getImagemLivro() {
		return imagemLivro;
	}

	public void setImagemLivro(byte[] imagemLivro) {
		this.imagemLivro = imagemLivro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public List<Autor> getAutor() {
		return autor;
	}

	public void setAutor(List<Autor> autor) {
		this.autor = autor;
	}

	public List<ItemPedido> getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(List<ItemPedido> itemPedido) {
		this.itemPedido = itemPedido;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
