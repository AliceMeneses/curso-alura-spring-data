package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {
	
	private final CargoRepository repository;

	public CrudCargoService(CargoRepository repository) {
		this.repository = repository;
	}
	
	public void inicial(Scanner scanner) {
		boolean system = true;
		int action;
		
		while(system) {
			System.out.println("0 -> Voltar");
			System.out.println("1 -> Salvar cargo");
			System.out.println("2 -> Atualizar cargo");
			System.out.println("3 -> Visualizar cargos");
			System.out.println("4 -> Deletar cargo");

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
		System.out.print("Descrição do cargo: ");
		String descricao = scanner.next();
		
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		
		repository.save(cargo);
		
		System.out.println("Salvo");
		
	}
	
	public void atualizar(Scanner scanner) {
		System.out.print("Id do cargo: ");
		Integer id = scanner.nextInt();
		System.out.print("Nova descrição do cargo: ");
		String descricao = scanner.next();
		
		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		
		repository.save(cargo);		
		
		System.out.println("Atualizado");
	}
	
	public void deletar(Scanner scanner) {
		System.out.print("Id do cargo: ");
		Integer id = scanner.nextInt();
		
		repository.deleteById(id);
		
		System.out.println("Deletado");
	}
	
	public void visualizar() {
		Iterable<Cargo> cargos = repository.findAll();
		cargos.forEach(System.out::println);
	}
}
