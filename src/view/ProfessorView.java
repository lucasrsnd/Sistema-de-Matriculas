package src.view;

import src.model.Matricula;
import java.util.List;
import java.util.Scanner;

public class ProfessorView {

    private Scanner scanner = new Scanner(System.in);

    // Método para exibir o menu do professor
    public void exibirMenu() {
        System.out.println("\n=== Menu Professor ===");
        System.out.println("1. Visualizar minhas turmas");
        System.out.println("2. Visualizar alunos matriculados em uma disciplina");
        System.out.println("3. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");
    }

    // Método para obter a opção escolhida pelo professor
    public int obterOpcao() {
        return Integer.parseInt(scanner.nextLine());
    }

    // Método para exibir as turmas de um professor
    public void mostrarTurmas(List<String> turmas) {
        System.out.println("\nSuas turmas:");
        for (String turma : turmas) {
            System.out.println("- " + turma);
        }
    }

    // Método para mostrar os alunos matriculados em uma turma específica
    public void mostrarAlunos(List<Matricula> matriculas, String nomeTurma) {
        System.out.println("\nAlunos matriculados na turma " + nomeTurma + ":");
        for (Matricula matricula : matriculas) {
            if (matricula.getNomeTurma().equals(nomeTurma)) {
                System.out.println("- " + matricula.getNomeAluno());
            }
        }
    }
}

