package br.com.alura.spring.data.specification;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.alura.spring.data.orm.Funcionario;

public class SpecificationFuncionario implements Specification<Funcionario>{
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String cpf;
	private BigDecimal salario;
	private LocalDate dataContratacao;
	
	
	
	public SpecificationFuncionario(String nome, String cpf, BigDecimal salario, LocalDate dataContratacao) {
		this.nome = nome;
		this.cpf = cpf;
		this.salario = salario;
		this.dataContratacao = dataContratacao;
	}

	@Override
	public Predicate toPredicate(Root<Funcionario> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(this.nome != null) {
			predicates.add(criteriaBuilder.like(root.<String>get("nome"), "%" + nome + "%"));
		}
		
		if(this.cpf != null) {
			predicates.add(criteriaBuilder.equal(root.<String>get("cpf"), this.cpf));
		}
		
		if(this.salario != null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.<BigDecimal>get("salario"), this.salario));
		}
		
		if(this.dataContratacao != null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.<LocalDate>get("dataContratacao"), this.dataContratacao));
		}
		return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
	}
	
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
