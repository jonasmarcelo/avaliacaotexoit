package br.com.avaliacaotexoit.resource;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.avaliacaotexoit.helper.LocalServerPortHelper;
import br.com.avaliacaotexoit.model.IntervaloPremios;
import br.com.avaliacaotexoit.model.IntervaloPremiosDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FilmeResourceTest {

	@LocalServerPort
	private int port;

	@Autowired
	private LocalServerPortHelper helper;

	@Test
	public void getProdutoresMaiorMenorIntervaloPremioTest() {

		IntervaloPremios min = new IntervaloPremios("Joel Silver", 1, 1990, 1991);
		IntervaloPremios max = new IntervaloPremios("Matthew Vaughn", 13, 2002, 2015);
		IntervaloPremiosDto retornoEsperado = new IntervaloPremiosDto(Arrays.asList(min), Arrays.asList(max));

		HttpEntity<IntervaloPremiosDto> entity = new HttpEntity<>(null, this.helper.headers);

		ResponseEntity<IntervaloPremiosDto> responseEntity = this.helper.restTemplate.exchange(
				this.helper.createURLWithPort("/avaliacaotexoit/api/filme/produtoresMaiorMenorIntervaloPremio",
						this.port),	HttpMethod.GET,	entity, IntervaloPremiosDto.class);

		assertEquals(retornoEsperado.getMin().get(0).getInterval(),
				responseEntity.getBody().getMin().get(0).getInterval());
		assertEquals(retornoEsperado.getMax().get(0).getInterval(),
				responseEntity.getBody().getMax().get(0).getInterval());

	}

}
