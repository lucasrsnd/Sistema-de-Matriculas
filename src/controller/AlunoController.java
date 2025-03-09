package src.controller;

import src.model.Usuario;
import src.model.Turma;
import src.model.Matricula;
import src.view.AlunoView;

import java.io.*;
import java.util.List;

public class AlunoController {

    private AlunoView alunoView = new AlunoView();

    // Método para gerenciar o menu do aluno
    public void gerenciarMenu(String codigoAluno) {
        boolean sair = false;
        while (!sair) {
            alunoView.exibirMenu();
            int opcao = alunoView.obterOpcao();

            switch (opcao) {
                case 1:
                    matricularAluno(codigoAluno);
                    break;
                case 2:
                    gerenciarMatriculas(codigoAluno);
                    break;
                case 3:
                    visualizarBoleto(codigoAluno);
                    break;
                case 4:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Método para matricular o aluno
    private void matricularAluno(String codigoAluno) {
        String nomeTurma = alunoView.obterNomeTurma();
        String nomeCurso = alunoView.obterNomeCurso();

        // Verificar se a turma já está cheia e se o aluno já está matriculado
        if (Matricula.alunoJaMatriculado(codigoAluno, nomeTurma)) {
            System.out.println("Você já está matriculado nesta turma.");
        } else {
            // Adicionar matrícula
            Matricula matricula = new Matricula(codigoAluno, nomeTurma, nomeCurso, nomeCurso);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/Matriculas.txt", true))) {
                writer.write(matricula.toString());
                writer.newLine();
                System.out.println("Matrícula realizada com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao realizar matrícula: " + e.getMessage());
            }
        }
    }

    // Método para gerenciar as matrículas do aluno
    private void gerenciarMatriculas(String codigoAluno) {
        List<Matricula> matriculas = Matricula.buscarMatriculasPorAluno(codigoAluno);
        alunoView.mostrarMatriculas(matriculas);

        System.out.print("Digite o nome da turma para cancelar a matrícula: ");
        String nomeTurma = alunoView.obterNomeTurma();

        if (Matricula.cancelarMatricula(codigoAluno, nomeTurma)) {
            System.out.println("Matrícula cancelada com sucesso!");
        } else {
            System.out.println("Erro ao cancelar a matrícula. Você não está matriculado nesta turma.");
        }
    }

    // Método para visualizar o boleto do aluno
    private void visualizarBoleto(String codigoAluno) {
        List<Matricula> matriculas = Matricula.buscarMatriculasPorAluno(codigoAluno);
        alunoView.mostrarBoleto(matriculas);
    }
}

