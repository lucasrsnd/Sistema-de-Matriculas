package src.controller;

import src.model.Matricula;
import src.view.ProfessorView;
import java.io.*;
import java.util.*;

public class ProfessorController {

    private ProfessorView professorView = new ProfessorView();

    public void gerenciarMenu(String codigoProfessor) {
        boolean continuar = true;
        while (continuar) {
            professorView.exibirMenu();
            int opcao = professorView.obterOpcao();

            switch (opcao) {
                case 1:
                    mostrarTurmas(codigoProfessor);
                    break;
                case 2:
                    visualizarAlunos(codigoProfessor);
                    break;
                case 3:
                    continuar = false;
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private void mostrarTurmas(String codigoProfessor) {
        List<String> turmas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/data/Turmas.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split("\\|");

                if (dados.length >= 6 && dados[4].equals(codigoProfessor)) {
                    turmas.add(dados[0]);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler turmas: " + e.getMessage());
        }

        professorView.mostrarTurmas(turmas);
    }

    private void visualizarAlunos(String codigoProfessor) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome da turma: ");
        String nomeTurma = scanner.nextLine();

        System.out.print("Digite o nome do curso: ");
        String nomeCurso = scanner.nextLine();

        List<String> alunosMatriculados = buscarAlunosMatriculados(nomeTurma, nomeCurso);

        if (alunosMatriculados.isEmpty()) {
            System.out.println("Não há alunos matriculados nesta turma e curso.");
        } else {
            System.out.println("Alunos matriculados na turma '" + nomeTurma + "' do curso '" + nomeCurso + "':");
            for (String aluno : alunosMatriculados) {
                System.out.println("- " + aluno);
            }
        }
    }

    private List<String> buscarAlunosMatriculados(String nomeTurma, String nomeCurso) {
        List<String> alunosMatriculados = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/data/Matriculas.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split("\\|");

                if (dados.length >= 4 && dados[2].equals(nomeTurma) && dados[3].equals(nomeCurso)) {
                    alunosMatriculados.add(dados[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler matrículas: " + e.getMessage());
        }

        return alunosMatriculados;
    }
}
