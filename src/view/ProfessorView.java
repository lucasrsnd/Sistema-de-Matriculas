package src.view;

import src.model.Matricula;
import java.util.List;
import java.util.Scanner;

public class ProfessorView {

    public void exibirMenu() {
        System.out.println("\n=== Menu Professor ===");
        System.out.println("1. Mostrar Turmas");
        System.out.println("2. Visualizar Alunos Matriculados");
        System.out.println("3. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");
    }

    public int obterOpcao() {
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    public void mostrarTurmas(List<String> turmas) {
        System.out.println("\nTurmas disponíveis:");
        for (String turma : turmas) {
            System.out.println("- " + turma);
        }
    }

    public void mostrarAlunosPorTurmaECurso(List<Matricula> matriculas, String nomeTurma, String nomeCurso) {
        boolean encontrouAlunos = false;
        System.out.println("\nAlunos matriculados na turma '" + nomeTurma + "' do curso '" + nomeCurso + "':");
        for (Matricula matricula : matriculas) {
            if (matricula.getNomeTurma().equals(nomeTurma) && matricula.getNomeCurso().equals(nomeCurso)) {
                System.out.println("- " + matricula.getNomeAluno());
                encontrouAlunos = true;
            }
        }
        if (!encontrouAlunos) {
            System.out.println("Não há alunos matriculados nesta turma e curso.");
        }
    }
}
