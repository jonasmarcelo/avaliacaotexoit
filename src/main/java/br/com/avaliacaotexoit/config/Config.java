package br.com.avaliacaotexoit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.avaliacaotexoit.service.DBService;

@Configuration
public class DevConfig {

	@Autowired
	private DBService dbService;

	@Bean
	public boolean instanciaBaseDeDados() {

		this.dbService.instanciaBaseDeDados();

		return false;
	}
}
