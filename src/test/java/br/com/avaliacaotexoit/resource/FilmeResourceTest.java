package br.com.avaliacaotexoit.resource;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import br.com.avaliacaotexoit.model.IntervaloPremios;
import br.com.avaliacaotexoit.model.IntervaloPremiosDto;
import br.com.avaliacaotexoit.repository.FilmeRepository;
import br.com.avaliacaotexoit.service.FilmeService;
import io.restassured.http.ContentType;

@WebMvcTest
public class FilmeResourceTest {

	@Autowired
	private FilmeResource filmeResource;

	@MockBean
	private FilmeService filmeService;

	@MockBean
	private FilmeRepository filmeRepository;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.filmeResource);
	}

	@Test
	public void testaGetProdutoresMaiorMenorIntervaloPremio() {

		List<IntervaloPremios> min = new ArrayList<IntervaloPremios>();
		IntervaloPremios intervaloMin = new IntervaloPremios("Joel Silver", 1, 1990, 1991);
		min.add(intervaloMin);
		
		List<IntervaloPremios> max = new ArrayList<IntervaloPremios>();
		IntervaloPremios intervaloMax = new IntervaloPremios("Matthew Vaughn", 13, 2002, 2015);
		max.add(intervaloMax);
		
		IntervaloPremiosDto intervalosPremios = new IntervaloPremiosDto(min, max);
		
		when(this.filmeService.getProdutoresMaiorMenorIntervaloPremio()).thenReturn(intervalosPremios);

		given().accept(ContentType.JSON).when().get("/avaliacaotexoit/api/filme/produtoresMaiorMenorIntervaloPremio")
				.then().statusCode(HttpStatus.OK.value());
	}

}
