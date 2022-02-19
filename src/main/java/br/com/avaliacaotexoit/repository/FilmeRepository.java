package br.com.avaliacaotexoit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.avaliacaotexoit.model.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long>{

	@Query("SELECT f FROM Filme f"
			+ " WHERE f.vencedor = true"
			+ " ORDER BY f.ano")
	List<Filme> findAllVencedores();
}
