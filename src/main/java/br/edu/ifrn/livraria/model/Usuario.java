package br.edu.ifrn.livraria.model;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
public class Usuario implements UserDetails {
 
	private static final long serialVersionUID = 1L; // estava comentada
	
	public Usuario() {
		this.dataCriacao = Calendar.getInstance().getTime();
		this.enabled = true;
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 40)
	@NotBlank(message = "Nome é uma informação obrigatória.")
	private String nome;
	
	@Column(nullable = false, length = 60)
	@NotBlank(message = "Sobrenome é uma informação obrigatória.")
	private String sobrenome;
	
	
	@Column(nullable = false, unique = true, length = 60)
	@NotBlank(message = "Email é uma informação obrigatória.")
	private String email;
	
	@Column(unique = true)
	@Size(min = 4, message = "USERNAME dev ter pelo menos 4 letras")
	private String username;
	
	@Column(nullable = false, length = 255)
	@NotBlank(message = "Senha é uma informação obrigatória.")
	private String senha; 
		
	@Column(nullable = false, length = 15)
	@NotBlank(message = "Rua é uma informação obrigatória.")
	private String rua;
	
	@Column(nullable = false, length = 15)
	@NotBlank(message = "Bairro é uma informação obrigatória.")
	private String bairro;
	
	@Column(nullable = false, length = 15)
	@NotBlank(message = "CEP é uma informação obrigatória.")
	private String cep;
	
	private String uf;
	
	@OneToMany(mappedBy="usuario")
	private List<Pedido> pedido;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	private Set<Role> role = new HashSet<Role>();
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataUpdate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimoAcesso;
	

	private boolean accountNonExpired;

	private boolean accountNonLocked;

	private boolean credentialsNonExpired;

	private boolean enabled;

	@ManyToOne
	@JoinColumn(name = "cidade_id_fk")
	private Cidade cidade;
	
	@Column(nullable =false, length=100)
	private String municipio;

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
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

	public List<Pedido> getPedido() {
		return pedido;
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

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataUpdate() {
		return dataUpdate;
	}

	public void setDataUpdate(Date dataUpdate) {
		this.dataUpdate = dataUpdate;
	}

	public Date getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(Date ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.addAll(getRole());
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public String getRoleString() {
		String txt="";
		if(role!=null) {
			for(Role r: role) {
				txt+= r.getNome() + ", "; 
			}
		}
		return txt;
}
}
