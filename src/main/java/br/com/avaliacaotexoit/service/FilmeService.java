package br.com.avaliacaotexoit.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.avaliacaotexoit.model.Filme;
import br.com.avaliacaotexoit.repository.FilmeRepository;

@Service
public class FilmeService {

	@Autowired
	private FilmeRepository repository;

	public void salvar(Filme filme) {
		this.repository.save(filme);
	}

	public List<Filme> findAllFilmes() {
		return this.repository.findAll();
	}

	public Filme findById(Long id) {
		return this.repository.findById(id).orElse(null);
	}
	
	public void deleteAll() {
		this.repository.deleteAll();
	}

}
