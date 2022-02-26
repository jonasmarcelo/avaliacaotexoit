package br.com.avaliacaotexoit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.avaliacaotexoit.model.Filme;
import br.com.avaliacaotexoit.model.FilmeComparatorAnoCrescente;
import br.com.avaliacaotexoit.model.IntervaloPremios;
import br.com.avaliacaotexoit.model.IntervaloPremiosCrescenteComparator;
import br.com.avaliacaotexoit.model.IntervaloPremiosDto;
import br.com.avaliacaotexoit.repository.FilmeRepository;

@Service
public class FilmeService {

	@Autowired
	private FilmeRepository repository;

	/**
	 * Método responsável por obter o produtor com maior intervalo entre dois
	 * prêmios consecutivos, e o que obteve dois prêmios mais rápido, seguindo a
	 * especificação de formato definida na página 2 do teste;
	 * 
	 * @return IntervaloPremiosDto
	 */
	public IntervaloPremiosDto getProdutoresMaiorMenorIntervaloPremio() {

		List<Filme> filmesVencedores = this.repository.findAllVencedores();

		Map<String, List<Filme>> mapProdutorFilmesVencedores = this.montaMapaProdutorFilmesVencedores(filmesVencedores);

		List<IntervaloPremios> listaIntervaloPremios = this.calculaIntervalosPremios(mapProdutorFilmesVencedores);

		Optional<IntervaloPremios> intervaloMin = listaIntervaloPremios.stream()
				.min(new IntervaloPremiosCrescenteComparator());

		List<IntervaloPremios> intervalosMin = listaIntervaloPremios.stream().filter(i -> i.equals(intervaloMin.get()))
				.collect(Collectors.toList());

		Optional<IntervaloPremios> intervaloMax = listaIntervaloPremios.stream()
				.max(new IntervaloPremiosCrescenteComparator());

		List<IntervaloPremios> intervalosMax = listaIntervaloPremios.stream().filter(i -> i.equals(intervaloMax.get()))
				.collect(Collectors.toList());

		return new IntervaloPremiosDto(intervalosMin, intervalosMax);
	}

	/**
	 * Monta um mapa com o produtor e seus filmes vencedores
	 * 
	 * @return Map<String, List<Filme>>
	 */
	private Map<String, List<Filme>> montaMapaProdutorFilmesVencedores(List<Filme> filmesVencedores) {
		return filmesVencedores.stream().collect(Collectors.groupingBy(f -> f.getProdutor()));
	}

	/**
	 * Monta mapa com o produtor e seus intervalos
	 * 
	 * @return Map<String, List<IntervaloPremios>>
	 */
	private List<IntervaloPremios> calculaIntervalosPremios(Map<String, List<Filme>> mapProdutorFilmesVencedores) {

		List<IntervaloPremios> listaIntervalorPremios = new ArrayList<IntervaloPremios>();

		mapProdutorFilmesVencedores.entrySet().stream().forEach(produtorFilmesVencedores -> {

			String produtor = produtorFilmesVencedores.getKey();

			List<Filme> filmesVencedores = produtorFilmesVencedores.getValue();

			if (filmesVencedores.size() > 1) {

				Optional<Filme> anoMin = filmesVencedores.stream().min(new FilmeComparatorAnoCrescente());
				Optional<Filme> anoMax = filmesVencedores.stream().max(new FilmeComparatorAnoCrescente());
				
				int intervalo = anoMax.get().getAno() - anoMin.get().getAno();
				IntervaloPremios intervaloPremio = new IntervaloPremios(produtor, intervalo, anoMin.get().getAno(),
						anoMax.get().getAno());
				listaIntervalorPremios.add(intervaloPremio);

			}
		});

		return listaIntervalorPremios;

	}

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
