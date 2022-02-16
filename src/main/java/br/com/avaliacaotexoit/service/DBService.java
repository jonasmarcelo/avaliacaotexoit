package br.com.avaliacaotexoit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.avaliacaotexoit.model.Filme;

@Service
public class DBService {

	@Autowired
	private FilmeService filmeService;

	public void instanciaBaseDeDados() {
		
		this.filmeService.deleteAll();

		Filme f1 = new Filme(1980, "Can't Stop the Music", "Associated Film Distribution", "Allan Carr", true);
		Filme f2 = new Filme(1980, "Cruising", "Lorimar Productions", "United Artists	Jerry Weintraub", false);
		Filme f3 = new Filme(1980, "The Formula	MGM", "United Artists", "Steve Shagan", false);

		this.filmeService.salvar(f1);
		this.filmeService.salvar(f2);
		this.filmeService.salvar(f3);

	}

}
