package src.view;

import java.util.Scanner;

public class AdminView {

    private Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {
        System.out.println("\n=== Menu Administrador ===");
        System.out.println("1. Criar Turma");
        System.out.println("2. Criar Usuário");
        System.out.println("3. Visualizar Turmas Criadas");
        System.out.println("4. Gerenciar Matrículas");
        System.out.println("5. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");
    }

    public int obterMinAlunos() {
        System.out.print("Digite a quantidade mínima de alunos: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int obterMaxAlunos() {
        System.out.print("Digite a quantidade máxima de alunos: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public String obterCodigoProfessor() {
        System.out.print("Digite o código do professor: ");
        return scanner.nextLine();
    }

    public double obterPrecoMensalidade() {
        System.out.print("Digite o preço da mensalidade: ");
        return Double.parseDouble(scanner.nextLine());
    }

    public String obterTipoUsuario() {
        System.out.print("Digite o tipo de usuário (Aluno ou Professor): ");
        return scanner.nextLine();
    }

    public String obterCodigoUsuario() {
        System.out.print("Digite o código do usuário: ");
        return scanner.nextLine();
    }

    public String obterNomeUsuario() {
        System.out.print("Digite o nome do usuário: ");
        return scanner.nextLine();
    }

    public String obterNomeTurma() {
        System.out.print("Digite o nome da turma: ");
        return scanner.nextLine();
    }

    public String obterNomeCurso() {
        System.out.print("Digite o nome do curso: ");
        return scanner.nextLine();
    }

    public String obterCodigoAluno() {
        System.out.print("Digite o código do aluno: ");
        return scanner.nextLine();
    }

    public String obterNomeAluno() {
        System.out.print("Digite o nome do aluno: ");
        return scanner.nextLine();
    }

    public int obterOpcao() {
        System.out.print("Escolha uma opção: ");
        return Integer.parseInt(scanner.nextLine());
    }

}
