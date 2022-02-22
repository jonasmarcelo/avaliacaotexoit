package br.com.avaliacaotexoit.model;

import java.util.Comparator;

public class IntervaloPremiosCrescenteComparator implements Comparator<IntervaloPremios>{

	@Override
	public int compare(IntervaloPremios intervalo1, IntervaloPremios intervalo2) {
		
		if (intervalo1.getInterval() > intervalo2.getInterval()) {
			return 1;
		}

		return -1;
	}

}
