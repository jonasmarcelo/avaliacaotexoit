package br.com.avaliacaotexoit.resource;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.avaliacaotexoit.model.IntervaloPremiosDto;
import br.com.avaliacaotexoit.service.FilmeService;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class FilmeResourceTest {
	
	@Autowired
	private FilmeService service;

	@Test
	public void testeGetProdutoresMaiorMenorIntervaloPremio() {
		IntervaloPremiosDto produtoresMaiorMenorIntervaloPremio = this.service.getProdutoresMaiorMenorIntervaloPremio();
		
		assertNotNull(produtoresMaiorMenorIntervaloPremio);
	}
	
}
