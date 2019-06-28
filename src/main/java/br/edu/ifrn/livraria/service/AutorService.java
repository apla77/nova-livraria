package br.edu.ifrn.livraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.livraria.model.Autor;
import br.edu.ifrn.livraria.repository.AutorRepository;

@Service
public class AutorService { 
	
	@Autowired
	private AutorRepository repository;

	public List<Autor> listaAll(){
		return repository.findAll();
	}
	
	public void cadastrar(Autor autor) {
		repository.saveAndFlush(autor);
	}
	
	public Autor findOne(Long id) {
        return repository.getOne(id);
    }
     
    public Autor save(Autor autor) {
        return repository.saveAndFlush(autor);
    }
     
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Autor> buscarPorNome(String nome){
		return repository.findByName(nome);
	}

}
