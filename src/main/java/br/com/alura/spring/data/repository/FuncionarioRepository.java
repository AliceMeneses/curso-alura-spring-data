package br.com.alura.spring.data.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;

public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>,
	JpaSpecificationExecutor<Funcionario>{
	
	List<Funcionario> findByNome(String nome);
	
	@Query("Select f from Funcionario f where f.nome = :pNome and f.dataContratacao = :pData and f.salario > :pSalario")
	List<Funcionario> findNomeDataContratacaoSalarioMaior(String pNome, LocalDate pData, BigDecimal pSalario);

	@Query(value = "SELECT * FROM FUNCIONARIO WHERE DATA_CONTRATACAO >= :pDataContratacao", nativeQuery = true)
	List<Funcionario> findDataContratacao(LocalDate pDataContratacao);
	
	@Query(value = "SELECT f.id, f.nome, f.salario  FROM Funcionario f", nativeQuery=true)
	List<FuncionarioProjecao> imprimiFuncionarios();
}
