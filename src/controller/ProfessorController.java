package src.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.model.Matricula;
import src.view.ProfessorView;

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
                    continuar = false; // Encerra o menu apenas, mas não o programa
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
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
    
                // Verifica se o código do professor corresponde
                String codigoProfessorNaLinha = dados[4].trim();
                if (dados.length >= 6 && codigoProfessorNaLinha.equals(codigoProfessor.trim())) {
                    turmas.add(dados[0]); // Nome da turma
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler turmas: " + e.getMessage());
        }
    
        // Exibe as turmas encontradas
        professorView.mostrarTurmas(turmas);
    }
    

    // Método para visualizar os alunos matriculados em uma turma

    private void visualizarAlunos(String codigoProfessor) {
    // Perguntar ao professor qual turma ele deseja visualizar os alunos
    Scanner scanner = new Scanner(System.in);
    System.out.print("Digite o nome da turma: ");
    String nomeTurma = scanner.nextLine();

    // Buscar matrículas da turma usando o método agora correto
    List<Matricula> matriculasTurma = Matricula.buscarMatriculasPorTurma(nomeTurma);

    // Exibe os alunos matriculados na turma
    if (!matriculasTurma.isEmpty()) {
        professorView.mostrarAlunos(matriculasTurma, nomeTurma);
    } else {
        System.out.println("Nenhum aluno matriculado na turma " + nomeTurma + ".");
    }
}

 
}