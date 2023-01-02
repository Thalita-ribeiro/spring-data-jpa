package br.com.alura.spring.data.service;

import br.com.alura.spring.data.entities.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {

    private Boolean system = true;
    private final CargoRepository cargoRepository;

    public CrudCargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public void initial(Scanner scanner) {
        while (system) {
            System.out.println("Qual acao de cargo deseja executar");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Excluir");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    save(scanner);
                case 2:
                    update(scanner);
                case 3:
                    visualize();
                case 4:
                    delete(scanner);
                    break;
                default:
                    system = false;
            }
        }

        save(scanner);
    }

    private void save(Scanner scanner) {
        System.out.println("Descricao do cargo");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Salvo");
    }

    private void update(Scanner scanner) {
        System.out.println("id");
        int id = scanner.nextInt();
        System.out.println("Descricao do cargo");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Atualizado");
    }

    private void delete(Scanner scanner) {
        System.out.println("id");
        int id = scanner.nextInt();
        cargoRepository.deleteById(id);
    }

    private void visualize() {
        Iterable<Cargo> cargos = cargoRepository.findAll();
        cargos.forEach(cargo -> System.out.println(cargo));
    }
}
