package br.com.alura.spring.data.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.alura.spring.data.orm.Funcionario;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer>{
	
	List<Funcionario> findByNome(String nome);
	
	@Query("Select f Funcionario f where f.nome = :pNome and f.data = :pData f.salario > :pSalario")
	List<Funcionario> findNomeDataContratacaoSalarioMaior(String pNome, LocalDate pData, BigDecimal pSalario);

}
