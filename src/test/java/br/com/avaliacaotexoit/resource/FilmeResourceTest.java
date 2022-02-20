package br.com.avaliacaotexoit.resource;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmeResourceTest {

	public MockMvc mvc;
	
	@Autowired
	public WebApplicationContext context;

	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void testeGetProdutoresMaiorMenorIntervaloPremio() throws Exception {
		String url = "/avaliacaotexoit/api/filme/produtoresMaiorMenorIntervaloPremio";

		this.mvc.perform(get(url)).andExpect(status().isOk()).andExpect(jsonPath("producer", equalTo("producer")));
	}

}
