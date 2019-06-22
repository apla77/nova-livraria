package br.edu.ifrn.livraria.service;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifrn.livraria.model.Role;
import br.edu.ifrn.livraria.model.Usuario;
import br.edu.ifrn.livraria.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = repository.findByEmail(username);
		org.springframework.security.core.userdetails.User userFinal = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getPermissoes(user));
		System.out.println(userFinal.getAuthorities());
		return userFinal;
	}
	
	private Collection<? extends GrantedAuthority> getPermissoes(UserDetails usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		
		Set<Role> permissoes =  ((Usuario) usuario).getRole();
		for(Role r : permissoes ) {
			 authorities.add(new SimpleGrantedAuthority(r.getNome().toUpperCase()));
		}
		
		return authorities;
	}
	
	public boolean verificarSenha(String senha, Usuario usuario) {
		return passwordEncoder.matches(senha, usuario.getPassword());
	}
	
	public void add(Usuario usuario) {
		usuario.setSenha(passwordEncoder.encode(usuario.getPassword()));
		usuario.setDataCriacao(new Date());
		usuario.setEnabled(true);
		repository.saveAndFlush(usuario);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public List<Usuario> findAll() {
		return repository.findAll();
	}
	
	public Usuario getOne(Long id) {
		return repository.getOne(id);
	}
	
	public Usuario getEmail(String email) {
		return repository.findByEmail(email);
	}
	
	public Usuario findByEmailAndSenha(String email, String senha) {
		return repository.findByEmailAndSenha(email, senha);
	}
	public Usuario findByUsername(String username){
		return repository.findByUsername(username);
	}
	
	public List<Usuario> buscarPorNome(String nome){
		return repository.findByName(nome);
	}
	
}
