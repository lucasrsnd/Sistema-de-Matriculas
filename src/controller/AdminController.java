package src.controller;

import src.model.Usuario;
import src.model.Turma;
import src.model.Matricula;
import src.view.AdminView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdminController {

    private AdminView adminView = new AdminView();

    public void gerenciarMenu() {
        boolean sair = false;
        while (!sair) {
            adminView.exibirMenu();
            int opcao = adminView.obterOpcao();

            switch (opcao) {
                case 1:
                    criarTurma();
                    break;
                case 2:
                    criarUsuario();
                    break;
                case 3:
                    visualizarTurmasCriadas();
                    break;
                case 4:
                    gerenciarMatriculas();
                    break;
                case 5:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void criarTurma() {
        String nomeTurma = adminView.obterNomeTurma();
        String nomeCurso = adminView.obterNomeCurso();
        int minAlunos = adminView.obterMinAlunos();
        int maxAlunos = adminView.obterMaxAlunos();
        String codigoProfessor = adminView.obterCodigoProfessor();
        double precoMensalidade = adminView.obterPrecoMensalidade();

        Turma turma = new Turma(nomeTurma, nomeCurso, minAlunos, maxAlunos, codigoProfessor, precoMensalidade);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/Turmas.txt", true))) {
            writer.write(turma.toString());
            writer.newLine();
            System.out.println("Turma criada com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar a turma: " + e.getMessage());
        }
    }

    private void criarUsuario() {
        String tipoUsuario = adminView.obterTipoUsuario();
        String codigo = adminView.obterCodigoUsuario();
        String nome = adminView.obterNomeUsuario();

        Usuario usuario = new Usuario(codigo, nome, tipoUsuario);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/Usuarios.txt", true))) {
            writer.write(usuario.toString());
            writer.newLine();
            System.out.println("Usuário criado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o usuário: " + e.getMessage());
        }
    }

    private void visualizarTurmasCriadas() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/data/Turmas.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler as turmas: " + e.getMessage());
        }
    }

    private void gerenciarMatriculas() {

        visualizarTurmasCriadas();

        String nomeTurma = adminView.obterNomeTurma();

        if (verificarTurmaExistente(nomeTurma)) {

            System.out.println("Selecione uma ação:");
            System.out.println("1 - Adicionar aluno");
            System.out.println("2 - Remover aluno");
            System.out.println("3 - Sair");

            int opcao = adminView.obterOpcao();

            switch (opcao) {
                case 1:
                    adicionarAluno(nomeTurma);
                    break;
                case 2:
                    removerAluno(nomeTurma);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } else {
            System.out.println("Turma não encontrada.");
        }
    }

    private boolean verificarTurmaExistente(String nomeTurma) {
        boolean existe = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/data/Turmas.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.contains(nomeTurma)) {
                    existe = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao verificar turma: " + e.getMessage());
        }
        return existe;
    }

    private void adicionarAluno(String nomeTurma) {
        String codigoAluno = adminView.obterCodigoAluno();
        String nomeCurso = adminView.obterNomeCurso();
        String nomeAluno = adminView.obterNomeAluno();

        if (Matricula.alunoJaMatriculado(codigoAluno, nomeTurma)) {
            System.out.println("Este aluno já está matriculado nesta turma.");
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

    private void removerAluno(String nomeTurma) {
        String codigoAluno = adminView.obterCodigoAluno();

        if (Matricula.cancelarMatricula(codigoAluno, nomeTurma)) {
            System.out.println("Matrícula removida com sucesso!");
        } else {
            System.out.println("Aluno não encontrado ou não matriculado nesta turma.");
        }
    }

}
