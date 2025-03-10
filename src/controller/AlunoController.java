package src.controller;

import src.model.Matricula;
import src.view.AlunoView;

import java.io.*;
import java.util.List;

public class AlunoController {

    private AlunoView alunoView = new AlunoView();

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

    private void matricularAluno(String codigoAluno) {
        String nomeAluno = alunoView.obterNomeAluno();
        String nomeTurma = alunoView.obterNomeTurma();
        String nomeCurso = alunoView.obterNomeCurso();

        if (Matricula.alunoJaMatriculado(codigoAluno, nomeTurma)) {
            System.out.println("Você já está matriculado nesta turma.");
        } else {

            Matricula matricula = new Matricula(codigoAluno, nomeAluno, nomeTurma, nomeCurso);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/Matriculas.txt", true))) {
                writer.write(matricula.toString());
                writer.newLine();
                System.out.println("Matrícula realizada com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao realizar matrícula: " + e.getMessage());
            }
        }
    }

    private void gerenciarMatriculas(String codigoAluno) {
        List<Matricula> matriculas = Matricula.buscarMatriculasPorAluno(codigoAluno);
        alunoView.mostrarMatriculas(matriculas);

        System.out.print("Digite o nome da turma para cancelar a matrícula: ");
        String nomeTurma = alunoView.obterNomeTurma();

        if (cancelarMatricula(codigoAluno, nomeTurma)) {
            System.out.println("Matrícula cancelada com sucesso!");
        } else {
            System.out.println("Erro ao cancelar a matrícula. Você não está matriculado nesta turma.");
        }
    }

    private boolean cancelarMatricula(String codigoAluno, String nomeTurma) {
        File arquivo = new File("src/data/Matriculas.txt");
        File tempArquivo = new File("src/data/Matriculas_temp.txt");
        boolean matriculaRemovida = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split("\\|");

                if (dados.length >= 4 && dados[0].equals(codigoAluno) && dados[2].equals(nomeTurma)) {
                    matriculaRemovida = true;
                    continue;
                }

                writer.write(linha);
                writer.newLine();
            }

            if (matriculaRemovida) {
                arquivo.delete();
                tempArquivo.renameTo(arquivo);
            }

        } catch (IOException e) {
            System.out.println("Erro ao cancelar a matrícula: " + e.getMessage());
        }

        return matriculaRemovida;
    }

    private void visualizarBoleto(String codigoAluno) {
        List<Matricula> matriculas = Matricula.buscarMatriculasPorAluno(codigoAluno);
        alunoView.mostrarBoleto(matriculas);
    }
}
