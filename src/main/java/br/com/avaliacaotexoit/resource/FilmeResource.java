package br.com.avaliacaotexoit.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.avaliacaotexoit.model.IntervaloPremiosDto;
import br.com.avaliacaotexoit.service.FilmeService;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/avaliacaotexoit/api/filme")
public class FilmeResource {
	
//	@Autowired
//	private FilmeServiceOld service;
	
	@Autowired
	private FilmeService service;

	@GetMapping(path = "/produtoresMaiorMenorIntervaloPremio")
    public ResponseEntity<IntervaloPremiosDto> getProdutoresMaiorMenorIntervaloPremio(){
        return ResponseEntity.ok().body(this.service.getProdutoresMaiorMenorIntervaloPremio());
    }
}
