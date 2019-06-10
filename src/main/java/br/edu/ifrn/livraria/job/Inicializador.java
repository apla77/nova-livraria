package br.edu.ifrn.livraria.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.edu.ifrn.livraria.model.Cidade;
import br.edu.ifrn.livraria.model.Role;
import br.edu.ifrn.livraria.model.Usuario;
import br.edu.ifrn.livraria.service.RoleService;
import br.edu.ifrn.livraria.service.UsuarioService;

@Component
public class Inicializador implements ApplicationListener<ContextRefreshedEvent>{
	@Autowired
	private RoleService serviceRole;
	@Autowired
	private UsuarioService serviceUsuario;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("----- Criando Usuário ------");
	//	creatUsuarioAdmin();
	//	createUsuario();
		System.out.println("----- Usuário Criado com Sucesso! -----");
	}
	/*
	private void creatUsuarioAdmin() {
		Usuario usuario = new Usuario();
		usuario.setEmail("apla77@gmail.com");
		usuario.setSenha("123456");
		usuario.setBairro("Centro");
		usuario.setRua("7 de Maio");
		usuario.setMunicipio("Encanto");
		usuario.setNome("Mauricio");
		usuario.setSobrenome("F. Fernandes");
		Role role = serviceRole.getNome("ADMINISTRADOR");
		if(role == null) {
			role = new Role();
			role.setNome("ADMINISTRADOR");
			serviceRole.add(role);
			usuario.getRole().add(role);
			serviceUsuario.add(usuario);
		}
	}
	
	private void createUsuario() {
		Usuario usuario2 = new Usuario();
		usuario2.setEmail("maria@gmail.com");
		usuario2.setSenha("123456");
		usuario2.setBairro("Centro");
		usuario2.setRua("13 de Maio");
		usuario2.setMunicipio("Itapipoca");
		usuario2.setNome("Maria de Fátima");
		usuario2.setSobrenome("Ferreira Fernandes");
		Role role = serviceRole.getNome("CLIENTE");
		if(role == null) {
			role = new Role();
			role.setNome("CLIENTE");
			serviceRole.add(role);
			usuario2.getRole().add(role);
			serviceUsuario.add(usuario2);
		}
	}
	*/
}
