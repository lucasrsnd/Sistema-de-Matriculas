import java.util.Scanner;
import src.controller.AdminController;
import src.controller.AlunoController;
import src.controller.ProfessorController;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Sistema de Gerenciamento ===");
        System.out.println("1. Acessar como Administrador");
        System.out.println("2. Acessar como Aluno");
        System.out.println("3. Acessar como Professor");
        System.out.print("Escolha uma opção: ");
        int opcao = Integer.parseInt(scanner.nextLine());

        if (opcao == 1) {

            AdminController adminController = new AdminController();
            adminController.gerenciarMenu();
        } else if (opcao == 2) {

            System.out.print("Digite seu código de aluno: ");
            String codigoAluno = scanner.nextLine();
            AlunoController alunoController = new AlunoController();
            alunoController.gerenciarMenu(codigoAluno);
        } else if (opcao == 3) {

            System.out.print("Digite seu código de professor: ");
            String codigoProfessor = scanner.nextLine();
            ProfessorController professorController = new ProfessorController();
            professorController.gerenciarMenu(codigoProfessor);
        } else {
            System.out.println("Opção inválida! Tente novamente.");
        }
    }
}
