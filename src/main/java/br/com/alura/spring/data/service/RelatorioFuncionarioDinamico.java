package br.com.alura.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioDinamico {
	
	private final FuncionarioRepository funcionarioRepository;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		int acao;
		boolean continua = true;
		
		while(continua) {
			System.out.println("0 -> Voltar");
			System.out.println("1 -> Relatório dinâmico do funcionário (OR)");
			System.out.println("2 -> Relatório dinâmico restrito do funcionário (AND)");
			acao = scanner.nextInt();
			
			switch(acao) {
			case 1:
				relatorioDinamicoFuncionarioOR(scanner);
				break;
			case 2:
				relatorioDinamicoFuncionarioAND(scanner);
				break;
			default:
				continua = false;
				break;
			}
		}

	}
	
	public void relatorioDinamicoFuncionarioAND(Scanner scanner) {
		System.out.println("Digite o nome do funcionário: ");
		String nome = scanner.next();
		
		if(nome.equalsIgnoreCase("NULL")) {
			nome = null;
		}
				
		System.out.println("Digite o CPF do funcionário: ");
		String cpf = scanner.next();
		
		if(cpf.equalsIgnoreCase("NULL")) {
			cpf = null;
		}
		
		System.out.println("Digite o salário do funcionário: ");
		BigDecimal salario = scanner.nextBigDecimal();
		
		if(salario.equals(BigDecimal.ZERO)) {
			salario = null;
		}
		
		System.out.println("Digite a data de contração do funcionário: ");
		String data = scanner.next();
		
		LocalDate dataContratacao;
		if(data.equalsIgnoreCase("NULL")) {
			dataContratacao = null;
		} else {
			dataContratacao = LocalDate.parse(data, formatter);
		}
		
		SpecificationFuncionario specification = new SpecificationFuncionario(nome, cpf, salario, dataContratacao);
		List<Funcionario> funcionarios = funcionarioRepository.findAll(specification);
		
		funcionarios.forEach(System.out::println);
	}
	
	public void relatorioDinamicoFuncionarioOR(Scanner scanner) {
		
		System.out.println("Digite o nome do funcionário: ");
		String nome = scanner.next();
		
		if(nome.equalsIgnoreCase("NULL")) {
			nome = null;
		}
				
		System.out.println("Digite o CPF do funcionário: ");
		String cpf = scanner.next();
		
		if(cpf.equalsIgnoreCase("NULL")) {
			cpf = null;
		}
		
		System.out.println("Digite o salário do funcionário: ");
		BigDecimal salario = scanner.nextBigDecimal();
		
		if(salario.equals(BigDecimal.ZERO)) {
			salario = null;
		}
		
		System.out.println("Digite a data de contração do funcionário: ");
		String data = scanner.next();
		
		LocalDate dataContratacao;
		if(data.equalsIgnoreCase("NULL")) {
			dataContratacao = null;
		} else {
			dataContratacao = LocalDate.parse(data, formatter);
		}
		
		List<Funcionario> funcionarios = 
				funcionarioRepository.findAll(Specification.where(
						SpecificationFuncionario.nome(nome)
						.or(SpecificationFuncionario.cpf(cpf))
						.or(SpecificationFuncionario.salario(salario))
						.or(SpecificationFuncionario.dataContratacao(dataContratacao))
		));
		
		funcionarios.forEach(System.out::println);
	}
}
