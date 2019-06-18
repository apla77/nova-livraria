package br.edu.ifrn.livraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.livraria.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
public List<Usuario> findByNomeLike(String name);
	
	@Query
	public Usuario findByEmail(String email);
	@Query
	public Usuario findByEmailAndSenha(String email, String senha);
	@Query
	public Usuario findByNome(String nome);


}
