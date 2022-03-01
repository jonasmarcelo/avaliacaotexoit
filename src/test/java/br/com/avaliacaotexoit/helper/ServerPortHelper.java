package br.com.avaliacaotexoit.helper;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class ServerPortHelper {

	public HttpHeaders headers = new HttpHeaders();
	
	public TestRestTemplate restTemplate = new TestRestTemplate();
	
	public String createURLWithPort(String uri, int port) {
		return "http://localhost:" + port + uri;
	}
}
