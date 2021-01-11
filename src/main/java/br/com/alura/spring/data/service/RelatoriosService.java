package br.com.alura.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {
	
	private final FuncionarioRepository funcionarioRepository;
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		boolean continuar = true;
		int acao;
		
		while(continuar) {
			System.out.println("0 -> Voltar");
			System.out.println("1 -> Buscar funcionário por nome");
			System.out.println("2 -> Buscar funcionário por nome, data de contratação e salário");
			System.out.println("3 -> Buscar funcionário por data de contratação");
			System.out.print("Escolha uma ação: ");
			acao = scanner.nextInt();
			
			switch(acao) {
			case 1:
				buscaFuncionarioPorNome(scanner);
				break;
			case 2:
				buscaFuncionarioPorNomeDataContratacaoSalarioMaior(scanner);
				break;
			case 3:
				buscaFuncionarioPorDataContracao(scanner);
				break;
			default:
				continuar = false;
				break;
			}
		}
	}
	
	public void buscaFuncionarioPorNome(Scanner scanner) {
		
		System.out.print("Digite o nome do funcionário: ");
		String nome = scanner.next();
		
		List<Funcionario> funcionarios = funcionarioRepository.findByNome(nome);
		
		funcionarios.forEach(System.out::println);
	}
	
	public void buscaFuncionarioPorNomeDataContratacaoSalarioMaior(Scanner scanner) {
		System.out.print("Digite o nome do funcionário: ");
		String nome = scanner.next();
		
		System.out.print("Digite a data do funcionário: ");
		String data = scanner.next();
		
		System.out.print("Digite o salário do funcionário: ");
		BigDecimal salario = scanner.nextBigDecimal();
		
		LocalDate localdate = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		List<Funcionario> funcionarios = funcionarioRepository.findNomeDataContratacaoSalarioMaior(nome, localdate, salario);
		
		funcionarios.forEach(System.out::println);
	}
	
	public void buscaFuncionarioPorDataContracao(Scanner scanner) {
		System.out.print("Digite a data do funcionário: ");
		String data = scanner.next();
		
		LocalDate localdate = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		List<Funcionario> funcionarios = funcionarioRepository.findDataContratacao(localdate);
		
		funcionarios.forEach(System.out::println);

	}
}
