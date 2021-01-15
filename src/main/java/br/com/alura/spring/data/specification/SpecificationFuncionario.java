package br.com.alura.spring.data.specification;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import br.com.alura.spring.data.orm.Funcionario;

public class SpecificationFuncionario {
	
	public static Specification<Funcionario> nome(String nome) {
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.like(root.<String>get("nome"), "%" + nome + "%");
	}
	
	public static Specification<Funcionario> cpf(String cpf) {
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.equal(root.<String>get("cpf"),cpf);
	}
	
	public static Specification<Funcionario> salario(BigDecimal salario) {
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.greaterThan(root.<BigDecimal>get("salario"), salario);
	}
	
	public static Specification<Funcionario> dataContratacao(LocalDate dataContratacao) {
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.greaterThan(root.<LocalDate>get("dataContratacao"), dataContratacao);
	}
	
}
