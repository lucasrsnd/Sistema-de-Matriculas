package src.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import src.model.Turma;
import src.model.Usuario;
import src.view.AdminView;

public class AdminController {

    private AdminView adminView = new AdminView();

    // Método para gerenciar o menu do administrador
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
                    sair = true;  // Esse "sair" vai sair do loop, mas não encerra o programa
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
    

    // Método para criar uma turma
    private void criarTurma() {
        String nomeTurma = adminView.obterNomeTurma();
        String nomeCurso = adminView.obterNomeCurso();
        int minAlunos = adminView.obterMinAlunos();
        int maxAlunos = adminView.obterMaxAlunos();
        String codigoProfessor = adminView.obterCodigoProfessor();
        double precoMensalidade = adminView.obterPrecoMensalidade();

        Turma turma = new Turma(nomeTurma, nomeCurso, minAlunos, maxAlunos, codigoProfessor, precoMensalidade);

        // Salvar a turma no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/Turmas.txt", true))) {
            writer.write(turma.toString());
            writer.newLine();
            System.out.println("Turma criada com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar a turma: " + e.getMessage());
        }
    }

    // Método para criar um usuário
    private void criarUsuario() {
        String tipoUsuario = adminView.obterTipoUsuario();
        String codigo = adminView.obterCodigoUsuario();
        String nome = adminView.obterNomeUsuario();

        Usuario usuario = new Usuario(codigo, nome, tipoUsuario);

        // Salvar o usuário no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/Usuarios.txt", true))) {
            writer.write(usuario.toString());
            writer.newLine();
            System.out.println("Usuário criado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o usuário: " + e.getMessage());
        }
    }

    // Método para visualizar turmas criadas
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

    // Método para gerenciar matrículas (a ser implementado)
    private void gerenciarMatriculas() {
        System.out.println("Funcionalidade de gerenciamento de matrículas ainda não implementada.");
    }
}

