package br.com.avaliacaotexoit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.avaliacaotexoit.model.Filme;
import br.com.avaliacaotexoit.model.IntervaloPremios;
import br.com.avaliacaotexoit.model.IntervaloPremiosDto;
import br.com.avaliacaotexoit.repository.FilmeRepository;

@Service
public class FilmeService {

	@Autowired
	private FilmeRepository repository;

	/**
	 * Método responsável por obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que
	 * obteve dois prêmios mais rápido, seguindo a especificação de formato definida na
	 * página 2 do teste;
	 * 
	 * @return IntervaloPremiosDto
	 */
	public IntervaloPremiosDto getProdutoresMaiorMenorIntervaloPremio() {

		List<Filme> filmes = this.findAllFilmes();

		List<Filme> filmesVencedores = this.repository.findAllVencedores();

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

		Map<String, List<IntervaloPremios>> mapProdutorIntervaloPremios = new HashMap<String, List<IntervaloPremios>>();
		for (Map.Entry<String, List<Filme>> entry : mapProdutorFilmesVencedores.entrySet()) {

			String produtor = entry.getKey();

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
					}

					anoMin = f.getAno();
					anoMax = f.getAno();

				}

			}

		}

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

		return new IntervaloPremiosDto(listaIntervalosMin, listaIntervalosMax);
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
