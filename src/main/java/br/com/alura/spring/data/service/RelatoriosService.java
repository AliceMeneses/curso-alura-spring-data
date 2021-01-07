package br.com.alura.spring.data.service;

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
			System.out.print("Escolha uma ação: ");
			acao = scanner.nextInt();
			
			switch(acao) {
			case 1:
				buscaFuncionarioPorNome(scanner);
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
}
