package src.model;

public class Turma {
    private String nomeTurma;
    private String nomeCurso;
    private int minAlunos;
    private int maxAlunos;
    private String codigoProfessor;
    private double precoMensalidade;

    public Turma(String nomeTurma, String nomeCurso, int minAlunos, int maxAlunos, String codigoProfessor,
            double precoMensalidade) {
        this.nomeTurma = nomeTurma;
        this.nomeCurso = nomeCurso;
        this.minAlunos = minAlunos;
        this.maxAlunos = maxAlunos;
        this.codigoProfessor = codigoProfessor;
        this.precoMensalidade = precoMensalidade;
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

    public int getMinAlunos() {
        return minAlunos;
    }

    public void setMinAlunos(int minAlunos) {
        this.minAlunos = minAlunos;
    }

    public int getMaxAlunos() {
        return maxAlunos;
    }

    public void setMaxAlunos(int maxAlunos) {
        this.maxAlunos = maxAlunos;
    }

    public String getCodigoProfessor() {
        return codigoProfessor;
    }

    public void setCodigoProfessor(String codigoProfessor) {
        this.codigoProfessor = codigoProfessor;
    }

    public double getPrecoMensalidade() {
        return precoMensalidade;
    }

    public void setPrecoMensalidade(double precoMensalidade) {
        this.precoMensalidade = precoMensalidade;
    }

    public String toString() {
        return nomeTurma + "|" + nomeCurso + "|" + minAlunos + "|" + maxAlunos + "|" + codigoProfessor + "|"
                + precoMensalidade;
    }
}
