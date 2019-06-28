package br.edu.ifrn.livraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.livraria.model.Categoria;
import br.edu.ifrn.livraria.model.Livro;
import br.edu.ifrn.livraria.model.Pedido;
import br.edu.ifrn.livraria.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository repository;

	public Livro save(Livro livro) {
		return repository.saveAndFlush(livro);
	}

	public List<Livro> listaAll() {
		return repository.findAll();
	}
	
	public Livro findOne(Long id) {
		return repository.getOne(id);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public List<Livro> findByPedido(Long id) {
		return repository.findByPedido(id);
	}
	
	public List<Livro> buscarPorTitulo(String titulo){
		return repository.findByTitulo(titulo);
	}

}
