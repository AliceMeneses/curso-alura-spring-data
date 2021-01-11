package br.com.alura.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudFuncionarioService {
	
	private final FuncionarioRepository funcionarioRepository;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	private final CargoRepository cargoRepository;

	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, UnidadeTrabalhoRepository unidadeTrabalhoRepository, CargoRepository cargoRepository) {
		this.funcionarioRepository = funcionarioRepository;
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
		this.cargoRepository = cargoRepository;
	}
	
	public void inicial(Scanner scanner) {
		boolean system = true;
		int action;
		
		while(system) {
			System.out.println("0 -> Voltar");
			System.out.println("1 -> Salvar funcionário");
			System.out.println("2 -> Atualizar funcionário");
			System.out.println("3 -> Visualizar funcionários ");
			System.out.println("4 -> Deletar funcionário");

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
		System.out.print("Nome do funcionário: ");
		String nome  = scanner.next();
		
		System.out.print("CPF do funcionário: ");
		String cpf  = scanner.next();
		
		System.out.print("Salário do funcionário: ");
		BigDecimal salario  = scanner.nextBigDecimal();
		
		System.out.print("Data de contração do funcionário: ");
		String dataContratacao  = scanner.next();
		
		System.out.print("Id do cargo do funcionário: ");
		Integer cargoId  = scanner.nextInt();
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		
		LocalDate dataContratacaoFormatada = LocalDate.parse(dataContratacao, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		funcionario.setDataContratacao(dataContratacaoFormatada);
		
		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());
		
		List<UnidadeTrabalho> unidades = unidade(scanner);
		funcionario.setUnidadeTrabalhos(unidades);
		
		funcionarioRepository.save(funcionario);
		
		System.out.println("Salvo");
		
	}
	
	private List<UnidadeTrabalho> unidade(Scanner scanner){
		
		int action;
		Boolean continuar = true;
		List<UnidadeTrabalho> unidades = new ArrayList<>();
		
		while(continuar) {
			System.out.println("0 -> Voltar");
			System.out.println("1 -> Vincular unidade de trabalho");
			System.out.print("Escolha uma ação: ");
			action = scanner.nextInt();
			
			if(action == 1) {
				System.out.print("Id da unidade de trabalho do funcionário: ");
				Integer id  = scanner.nextInt();
				Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(id);
				unidades.add(unidade.get());
			} else {
				continuar = false;
			}
		}
		
		return unidades;
	}
	
	public void atualizar(Scanner scanner) {
		System.out.print("Id do funcionário: ");
		Integer id  = scanner.nextInt();
		
		System.out.print("Nome do funcionário: ");
		String nome  = scanner.next();
		
		System.out.print("CPF do funcionário: ");
		String cpf  = scanner.next();
		
		System.out.print("Salário do funcionário: ");
		BigDecimal salario  = scanner.nextBigDecimal();
		
		System.out.print("Data de contração do funcionário: ");
		String dataContratacao  = scanner.next();
		
		System.out.print("Id do cargo do funcionário: ");
		Integer cargoId  = scanner.nextInt();
		
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		
		LocalDate dataContratacaoFormatada = LocalDate.parse(dataContratacao, DateTimeFormatter.ofPattern("dd/MM/yy"));
		
		funcionario.setDataContratacao(dataContratacaoFormatada);
		
		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());
		
		List<UnidadeTrabalho> unidades = unidade(scanner);
		funcionario.setUnidadeTrabalhos(unidades);
		
		funcionarioRepository.save(funcionario);
				
		System.out.println("Atualizado");
	}
	
	public void deletar(Scanner scanner) {
		System.out.print("Id do funcionário: ");
		Integer id  = scanner.nextInt();
		
		funcionarioRepository.deleteById(id);
		
		System.out.println("Deletado");
	}
	
	public void visualizar() {
		Iterable<Funcionario> funcionarios = funcionarioRepository.findAll();
		funcionarios.forEach(System.out::println);
	}
}
