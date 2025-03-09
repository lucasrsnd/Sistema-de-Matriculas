package src.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import src.model.Matricula;
import src.view.AlunoView;

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
                    sair = true; // Encerra o menu apenas, mas não o programa
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Método para obter o código do professor com base no nome da turma
private String obterCodigoProfessor(String nomeTurma) {
    // Buscar no arquivo de Turmas.txt para encontrar o código do professor
    try (BufferedReader reader = new BufferedReader(new FileReader("src/data/Turmas.txt"))) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split("\\|");
            if (dados[0].equals(nomeTurma)) {
                return dados[4]; // O código do professor está na posição 4 do arquivo de Turmas.txt
            }
        }
    } catch (IOException e) {
        System.out.println("Erro ao buscar código do professor: " + e.getMessage());
    }
    return null; // Se não encontrar, retorna null
}


    // Método para matricular o aluno
    private void matricularAluno(String codigoAluno) {
    String nomeTurma = alunoView.obterNomeTurma();
    String nomeCurso = alunoView.obterNomeCurso();

    // Aqui precisamos pegar o código do professor, não o nome do curso
    String codigoProfessor = obterCodigoProfessor(nomeTurma); // Você vai precisar implementar um método que obtenha o código do professor

    // Verificar se a turma já está cheia e se o aluno já está matriculado
    if (Matricula.alunoJaMatriculado(codigoAluno, nomeTurma)) {
        System.out.println("Você já está matriculado nesta turma.");
    } else {
        // Adicionar matrícula
        Matricula matricula = new Matricula(codigoAluno, nomeTurma, nomeCurso, codigoProfessor);
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

