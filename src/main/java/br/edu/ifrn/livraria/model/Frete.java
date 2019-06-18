package br.edu.ifrn.livraria.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Frete implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	private Double valor;
	
	private int totDias;
	
	private Double peso;
	
	private Date dataEntregaCorreios;
	
	private String dataEntregaCliente;
	
	private String cepOrigem;

	//@NotBlank(message = "Cep é uma informação obrigatória.")
	private String cep;
	
	private String uf;
	
	private String cidade;
	
	private String rua;
	
	private String bairro;
	
	@OneToOne
	@JoinColumn(name="pedido_id")
	public Pedido pedido;
		
	@OneToOne(mappedBy="frete")
	public ItemPedido itempedido;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public int getTotDias() {
		return totDias;
	}

	public void setTotDias(int totDias) {
		this.totDias = totDias;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Date getDataEntregaCorreios() {
		return dataEntregaCorreios;
	}

	public void setDataEntregaCorreios(Date dataEntregaCorreios) {
		this.dataEntregaCorreios = dataEntregaCorreios;
	}

	public String getDataEntregaCliente() {
		return dataEntregaCliente;
	}

	public void setDataEntregaCliente(String dataEntregaCliente) {
		this.dataEntregaCliente = dataEntregaCliente;
	}

	public String getCepOrigem() {
		return cepOrigem;
	}

	public void setCepOrigem(String cepOrigem) {
		this.cepOrigem = cepOrigem;
	}
	

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public ItemPedido getItempedido() {
		return itempedido;
	}

	public void setItempedido(ItemPedido itempedido) {
		this.itempedido = itempedido;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
