package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {
	
	private final UnidadeTrabalhoRepository repository;

	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository repository) {
		this.repository = repository;
	}
	
	public void inicial(Scanner scanner) {
		int action;
		boolean system = true;
		
		while(system) {
			System.out.println("0 -> Voltar");
			System.out.println("1 -> Salvar unidade de trabalho");
			System.out.println("2 -> Atualizar unidade de trabalho");
			System.out.println("3 -> Visualizar unidades de trabalho");
			System.out.println("4 -> Deletar unidade de trabalho");

			System.out.print("Escolha uma ação: ");
			action = scanner.nextInt();

			switch(action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(scanner);
				break;
			default:
				system = false;
				break;					
			}
	
		}
		
	}
	
	public void salvar(Scanner scanner) {
		System.out.print("Descrição da unidade de trabalho: ");
		String descricao = scanner.next();
		System.out.print("Endereço da unidade de trabalho: ");
		String endereco = scanner.next();
		
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setDescricao(descricao);
		unidadeTrabalho.setEndereco(endereco);
		
		repository.save(unidadeTrabalho);
		
		System.out.println("Salvo");
		
	}
	
	public void atualizar(Scanner scanner) {
		System.out.print("Id da unidade de trabalho: ");
		Integer id = scanner.nextInt();
		System.out.print("Nova descrição da unidade de trabalho: ");
		String descricao = scanner.next();
		System.out.print("Novo endereço da unidade de trabalho: ");
		String endereco = scanner.next();
		
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setId(id);
		unidadeTrabalho.setDescricao(descricao);
		unidadeTrabalho.setEndereco(endereco);
		
		repository.save(unidadeTrabalho);		
		
		System.out.println("Atualizado");
	}
	
	public void deletar(Scanner scanner) {
		System.out.print("Id da unidade de trabalho: ");
		Integer id = scanner.nextInt();
		
		repository.deleteById(id);
		
		System.out.println("Deletado");
	}
	
	public void visualizar() {
		Iterable<UnidadeTrabalho> unidades = repository.findAll();
		unidades.forEach(System.out::println);
	}
}
