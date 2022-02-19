package br.com.avaliacaotexoit.model;

import java.util.List;

public class IntervaloPremiosDto {

	private List<IntervaloPremios> min;
	private List<IntervaloPremios> max;
	
	public IntervaloPremiosDto() {
		super();
	}

	public IntervaloPremiosDto(List<IntervaloPremios> min, List<IntervaloPremios> max) {
		super();
		this.min = min;
		this.max = max;
	}

	public List<IntervaloPremios> getMin() {
		return min;
	}

	public void setMin(List<IntervaloPremios> min) {
		this.min = min;
	}

	public List<IntervaloPremios> getMax() {
		return max;
	}

	public void setMax(List<IntervaloPremios> max) {
		this.max = max;
	}

}
