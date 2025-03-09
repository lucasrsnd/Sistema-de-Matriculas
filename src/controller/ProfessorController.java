package src.controller;

import src.model.Turma;
import src.model.Matricula;
import src.view.ProfessorView;
import java.io.*;
import java.util.*;

public class ProfessorController {

    private ProfessorView professorView = new ProfessorView();

    // Método para gerenciar o menu do professor
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

    // Método para buscar e exibir as turmas do professor
    private void mostrarTurmas(String codigoProfessor) {
        List<String> turmas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/data/Turmas.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split("\\|");

                // Verifique se o número de colunas na linha é suficiente (deve ser 7)
                if (dados.length >= 7 && dados[6].equals(codigoProfessor)) {
                    turmas.add(dados[0]); // Nome da turma
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler turmas: " + e.getMessage());
        }

        professorView.mostrarTurmas(turmas);
    }

    // Método para visualizar os alunos matriculados em uma turma
    private void visualizarAlunos(String codigoProfessor) {
        List<Matricula> matriculas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/data/Matriculas.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split("\\|");

                // Verifique se o número de colunas na linha é suficiente (deve ser 4)
                if (dados.length >= 4 && dados[3].equals(codigoProfessor)) {
                    matriculas.add(new Matricula(dados[0], dados[1], dados[2], dados[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler matrículas: " + e.getMessage());
        }

        // Perguntar ao professor em qual turma ele deseja visualizar os alunos
        System.out.print("Digite o nome da turma: ");
        Scanner scanner = new Scanner(System.in);
        String nomeTurma = scanner.nextLine();

        professorView.mostrarAlunos(matriculas, nomeTurma);
    }
}