package com.AcademicoPlus.AcademicoPlus.models;

public class Usuario {
    protected String cpf;
    protected String nome;
    protected String senha;

    public Usuario(String cpf, String nome, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
    }

    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    public String getSenha() { return senha; }
}
