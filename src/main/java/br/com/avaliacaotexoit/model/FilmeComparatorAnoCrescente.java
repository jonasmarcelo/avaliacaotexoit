package br.com.avaliacaotexoit.model;

import java.util.Comparator;

public class FilmeComparatorAnoCrescente implements Comparator<Filme>{

	@Override
	public int compare(Filme f1, Filme f2) {
		
		if(f1.getAno() > f2.getAno()) {
			return 1;
		}
		
		return -1;
	}

}
