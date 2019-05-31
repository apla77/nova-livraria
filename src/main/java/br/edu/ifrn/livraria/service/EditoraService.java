package br.edu.ifrn.livraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.livraria.model.Editora;
import br.edu.ifrn.livraria.repository.EditoraRepository;

@Service
public class EditoraService {
	
	@Autowired
	private EditoraRepository repository;

	public List<Editora> listaAll(){
		return repository.findAll();
	}
	
	public void cadastrar(Editora editora) {
		repository.saveAndFlush(editora);
	}
	
	public Editora  findOne(Long id) {
        return repository.getOne(id);
    }
     
    public Editora save(Editora editora) {
        return repository.saveAndFlush(editora);
    }
     
    public void delete(Long id) {
        repository.deleteById(id);
    }


}
