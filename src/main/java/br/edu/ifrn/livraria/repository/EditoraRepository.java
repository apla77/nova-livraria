package br.edu.ifrn.livraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.livraria.model.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long>{

	public List<Editora> findByNomeLike(String name);
	
	@Query("select e from Editora e where e.nome = ?1")
	public List<Editora> findByName(String nome);

}
