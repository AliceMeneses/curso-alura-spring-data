package br.com.alura.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.service.CrudCargoService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner{
	
	private final CrudCargoService cargoService;
	private boolean system;

	
	public SpringDataApplication(CrudCargoService cargoService) {
		this.cargoService = cargoService;
		this.system = true;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		int acao;
		
		while(system) {
			System.out.println("0 -> Sair da aplicação");
			System.out.println("1 -> Cargo");
			System.out.print("Escolha uma ação: ");

			acao = scanner.nextInt();
			
			if(acao == 1) {
				cargoService.inicial(scanner);
			} else {
				system = false;
			}
			
		}
		
	}

}
