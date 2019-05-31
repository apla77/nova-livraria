package br.edu.ifrn.livraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.livraria.model.Cidade;
import br.edu.ifrn.livraria.repository.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository repository;

	public List<Cidade> listaAll(){
		return repository.findAll();
	}
	
	public void cadastrar(Cidade cidade) {
		repository.saveAndFlush(cidade);
	}
	
	public Cidade  findOne(Long id) {
        return repository.getOne(id);
    }
	
    public Cidade save(Cidade cidade) {
        return repository.saveAndFlush(cidade);
    }
     
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
