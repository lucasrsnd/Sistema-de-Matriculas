package src.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Matricula {
    private String codigoAluno;
    private String nomeAluno;
    private String nomeTurma;
    private String nomeCurso;

    public Matricula(String codigoAluno, String nomeAluno, String nomeTurma, String nomeCurso) {
        this.codigoAluno = codigoAluno;
        this.nomeAluno = nomeAluno;
        this.nomeTurma = nomeTurma;
        this.nomeCurso = nomeCurso;
    }

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

    @Override
    public String toString() {
        return codigoAluno + "|" + nomeAluno + "|" + nomeTurma + "|" + nomeCurso;
    }

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

    public static boolean alunoJaMatriculado(String codigoAluno, String nomeTurma) {
        List<Matricula> matriculas = buscarMatriculasPorAluno(codigoAluno);
        for (Matricula matricula : matriculas) {
            if (matricula.getNomeTurma().equals(nomeTurma)) {
                return true;
            }
        }
        return false;
    }

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
