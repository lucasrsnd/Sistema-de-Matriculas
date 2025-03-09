package src.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Matricula {
    private String codigoAluno;
    private String nomeTurma;
    private String nomeCurso;
    private String codigoProfessor; // Adicionando código do professor
    private String nomeAluno;
    
        // Construtor atualizado para incluir o código do professor
        public Matricula(String codigoAluno, String nomeTurma, String nomeCurso, String codigoProfessor) {
            this.codigoAluno = codigoAluno;
            this.nomeTurma = nomeTurma;
            this.nomeCurso = nomeCurso;
            this.codigoProfessor = codigoProfessor;
        }
    
        // Getters e Setters
        public String getCodigoAluno() {
            return codigoAluno;
        }
    
        public void setCodigoAluno(String codigoAluno) {
            this.codigoAluno = codigoAluno;
        }
    
        public String getNomeAluno() {
            return nomeAluno;
        }
    
        public void setNomeAluno(String nomeAluno) {
            this.nomeAluno = nomeAluno;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getCodigoProfessor() {
        return codigoProfessor;
    }

    public void setCodigoProfessor(String codigoProfessor) {
        this.codigoProfessor = codigoProfessor;
    }

    // Método para representar a matrícula como uma string para salvar no arquivo
    public String toString() {
        return codigoAluno + "|" + nomeTurma + "|" + nomeCurso + "|" + codigoProfessor;
    }

    // Método para buscar todas as matrículas de um aluno
    public static List<Matricula> buscarMatriculasPorAluno(String codigoAluno) {
        List<Matricula> matriculas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/data/Matriculas.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split("\\|");
                if (dados[0].equals(codigoAluno)) {
                    Matricula matricula = new Matricula(dados[0], dados[1], dados[2], dados[3]);
                    matriculas.add(matricula);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao buscar matrículas: " + e.getMessage());
        }
        return matriculas;
    }

    // Método para verificar se o aluno já está matriculado em uma turma
    public static boolean alunoJaMatriculado(String codigoAluno, String nomeTurma) {
        List<Matricula> matriculas = buscarMatriculasPorAluno(codigoAluno);
        for (Matricula matricula : matriculas) {
            if (matricula.getNomeTurma().equals(nomeTurma)) {
                return true;
            }
        }
        return false;
    }

    // Método para cancelar a matrícula
    public static boolean cancelarMatricula(String codigoAluno, String nomeTurma) {
        List<Matricula> matriculas = buscarMatriculasPorAluno(codigoAluno);
        for (Matricula matricula : matriculas) {
            if (matricula.getNomeTurma().equals(nomeTurma)) {
                matriculas.remove(matricula);
                return true;
            }
        }
        return false;
    }
}
