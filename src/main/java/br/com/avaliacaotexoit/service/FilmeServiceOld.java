package br.com.avaliacaotexoit.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.avaliacaotexoit.model.Filme;
import br.com.avaliacaotexoit.model.IntervaloPremios;
import br.com.avaliacaotexoit.model.IntervaloPremiosCrescenteComparator;
import br.com.avaliacaotexoit.model.IntervaloPremiosDecrescenteComparator;
import br.com.avaliacaotexoit.model.IntervaloPremiosDto;
import br.com.avaliacaotexoit.repository.FilmeRepository;

@Service
public class FilmeServiceOld {

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

		List<Filme> filmes = this.findAllFilmes();

		List<Filme> filmesVencedores = this.repository.findAllVencedores();

		Map<String, List<Filme>> mapProdutorFilmesVencedores = this.montaMapaProdutorFilmesVencedores(filmes,
				filmesVencedores);

		Map<String, List<IntervaloPremios>> mapProdutorIntervaloPremios = this
				.montaMapaProdutorIntervaloPremios(mapProdutorFilmesVencedores);

		List<IntervaloPremios> listaIntervalosMin = new ArrayList<IntervaloPremios>();
		List<IntervaloPremios> listaIntervalosMax = new ArrayList<IntervaloPremios>();
		for (Map.Entry<String, List<IntervaloPremios>> mapProdutorIntervalosPremios : mapProdutorIntervaloPremios
				.entrySet()) {

			IntervaloPremios intMin = mapProdutorIntervalosPremios.getValue().get(0);
			IntervaloPremios intMax = mapProdutorIntervalosPremios.getValue().get(0);
			for (IntervaloPremios i : mapProdutorIntervalosPremios.getValue()) {

				if (intMin.getInterval() > i.getInterval()) {
					intMin = i;
				}

				if (intMax.getInterval() < i.getInterval()) {
					intMax = i;
				}

			}

			listaIntervalosMin.add(intMin);
			listaIntervalosMax.add(intMax);

		}
		
		Collections.sort(listaIntervalosMin, new IntervaloPremiosCrescenteComparator());
		Collections.sort(listaIntervalosMax, new IntervaloPremiosDecrescenteComparator());

		List<IntervaloPremios> listaIntervalosMinFinais = new ArrayList<IntervaloPremios>();
		listaIntervalosMinFinais.add(listaIntervalosMin.get(0));
		IntervaloPremios intevaloMinFinal = null;
		for (IntervaloPremios intervaloMin : listaIntervalosMin) {
			
			if (intevaloMinFinal != null && intevaloMinFinal.getInterval() == intervaloMin.getInterval()) {
				listaIntervalosMinFinais.addAll(Arrays.asList(intervaloMin));
			}

			intevaloMinFinal = intervaloMin;
		}

		List<IntervaloPremios> listaIntervalosMaxFinais = new ArrayList<IntervaloPremios>();
		listaIntervalosMaxFinais.add(listaIntervalosMax.get(0));
		IntervaloPremios intevaloMaxFinal = null;
		for (IntervaloPremios intervaloMax : listaIntervalosMax) {

			if (intevaloMaxFinal != null && intevaloMaxFinal.getInterval() == intervaloMax.getInterval()) {
				listaIntervalosMaxFinais.addAll(Arrays.asList(intervaloMax));
			}

			intevaloMaxFinal = intervaloMax;
		}

		return new IntervaloPremiosDto(listaIntervalosMinFinais, listaIntervalosMaxFinais);
	}

	/**
	 * Monta um mapa com o produtor e seus filmes vencedores
	 * 
	 * @return Map<String, List<Filme>>
	 */
	private Map<String, List<Filme>> montaMapaProdutorFilmesVencedores(List<Filme> filmes,
			List<Filme> filmesVencedores) {

		Map<String, List<Filme>> mapProdutorFilmesVencedores = new HashMap<String, List<Filme>>();

		for (Filme f : filmes) {

			List<Filme> filmesVencedoresProdutor = new ArrayList<Filme>();
			for (Filme fVencedor : filmesVencedores) {

				if (f.isVencedor() && f.getProdutor().equals(fVencedor.getProdutor())) {
					filmesVencedoresProdutor.add(fVencedor);
					mapProdutorFilmesVencedores.put(fVencedor.getProdutor(), filmesVencedoresProdutor);
				}
			}
		}

		return mapProdutorFilmesVencedores;
	}

	/**
	 * Monta mapa com o produtor e seus intervalos
	 * 
	 * @return Map<String, List<IntervaloPremios>>
	 */
	private Map<String, List<IntervaloPremios>> montaMapaProdutorIntervaloPremios(
			Map<String, List<Filme>> mapProdutorFilmesVencedores) {

		Map<String, List<IntervaloPremios>> mapProdutorIntervaloPremios = new HashMap<String, List<IntervaloPremios>>();
		for (Map.Entry<String, List<Filme>> entry : mapProdutorFilmesVencedores.entrySet()) {

			String produtor = entry.getKey();

			//Só é possível calcular um intervalo entre dois prêmios quando o produtor possui mais de um filme vencedor
			if (entry.getValue().size() > 1) {

				List<IntervaloPremios> listaIntervalorPremios = new ArrayList<IntervaloPremios>();

				int anoMin = 0;
				int anoMax = 0;
				for (Filme f : entry.getValue()) {

					if (anoMin > 0 && anoMin > f.getAno()) {
						anoMin = f.getAno();
					}

					if (anoMax > 0 && anoMax < f.getAno()) {
						anoMax = f.getAno();
					}

					if (anoMin > 0 && anoMax > 0) {
						int intervalo = anoMax - anoMin;
						IntervaloPremios intervaloPremio = new IntervaloPremios(produtor, intervalo, anoMin, anoMax);
						listaIntervalorPremios.add(intervaloPremio);

						mapProdutorIntervaloPremios.put(produtor, listaIntervalorPremios);

						anoMin = 0;
						anoMax = 0;

						continue;
					}

					anoMin = f.getAno();
					anoMax = f.getAno();

				}

			}
		}

		return mapProdutorIntervaloPremios;
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
