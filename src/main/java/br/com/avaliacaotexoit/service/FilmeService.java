package br.com.avaliacaotexoit.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.avaliacaotexoit.model.Filme;
import br.com.avaliacaotexoit.model.IntervaloPremiosDto;
import br.com.avaliacaotexoit.repository.FilmeRepository;

@Service
public class FilmeService {

	@Autowired
	private FilmeRepository repository;

	public List<IntervaloPremiosDto> getProdutoresMaiorMenorIntervaloPremio() {
		
		List<Filme> filmes = this.repository.findAll();
		
		for (Filme filme : filmes) {
			
		}
		
		return null;
	}

}
