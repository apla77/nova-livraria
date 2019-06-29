package br.edu.ifrn.livraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.livraria.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{
	
	public List<Cidade> findByNomeLike(String name);
	
	@Query("select c from Categoria c where c.nome = ?")
	public List<Cidade> findByName(String nome);


}
