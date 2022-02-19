package br.com.avaliacaotexoit.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.avaliacaotexoit.model.Filme;

@Service
public class DBService {

	private static final String PATH = "filmes\\movielist_teste2.csv";

	@Autowired
	private FilmeService filmeService;

	/**
	 * Método que faz a leitura do arquivo de filmes e grava os dados no BD na inicialização da aplicação.
	 */
	public void instanciaBaseDeDados() {

		this.filmeService.deleteAll();

		List<Filme> filmes = this.getFilmesArquivo();
		
		filmes.forEach(filme -> this.filmeService.salvar(filme));

	}

	/**
	 * Método responsável por ler o arquivo.csv de filmes e devolver uma lista de Filmes
	 * 
	 * @return List<Filme>
	 */
	public List<Filme> getFilmesArquivo() {
		
		List<Filme> filmes = new ArrayList<Filme>();

		try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {

			String linha = br.readLine();
			linha = br.readLine();
			while (linha != null) {
				String[] linhaVetor = linha.split(";");

				int ano = Integer.valueOf(linhaVetor[0]);
				String titulo = linhaVetor[1];
				String estudio = linhaVetor[2];
				String produtor = linhaVetor[3];

				boolean vencedor;
				if (linhaVetor.length < 5) {
					vencedor = false;
				} else {
					vencedor = (linhaVetor[4]).equals("yes") ? true : false;
				}
				
				Filme filme = new Filme(ano, titulo, estudio, produtor, vencedor);
				
				filmes.add(filme);
				
				linha = br.readLine();
			}
			
		} catch (Exception e) {
			throw new RuntimeException("Erro ao ler o arquivo!: " + e.getMessage());
		}
		
		return filmes;
	}

}
