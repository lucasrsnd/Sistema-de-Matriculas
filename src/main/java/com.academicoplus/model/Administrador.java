package com.academicoplus.model;

import java.util.Date;
import java.util.List;

// Classe Administrador
public class Administrador extends Usuario {
    private String codigo;

    public void definirCurso(String curso) {}
    public void criarTurma(String disciplina, String turma) {}
    public void visualizarMatriculas() {}
    public void definirPeriodoMatricula(Date data) {}
    public void gerenciarUsuarios(String disciplina) {}
    public boolean excluirUsuario(Usuario usuario) { return false; }
}