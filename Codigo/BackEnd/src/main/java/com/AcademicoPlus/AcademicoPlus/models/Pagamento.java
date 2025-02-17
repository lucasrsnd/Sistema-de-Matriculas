package com.AcademicoPlus.AcademicoPlus.models;

import java.util.Date;

// Classe Pagamento
public class Pagamento {
    private int id;
    private Aluno aluno;
    private double valor;
    private Date vencimento;
    private String codigo;

    public boolean gerarBoleto() { return false; }
    public boolean pagarBoleto() { return false; }
    public void notificarCancelamento(Aluno aluno) {}
}