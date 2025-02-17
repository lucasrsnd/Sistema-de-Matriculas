package com.TechFrotas.TechFrotas.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "gastos_veiculos")
public class GastosVeiculosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veiculo_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CaminhaoModel veiculo;

    @Column(nullable = false)
    private String tipo; // "seguro" ou "financiamento"

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private int parcela;

    @Column(nullable = false)
    private int numeroParcelasTotal;

    // Getters e setters

    public int getNumeroParcelasTotal() {
        return numeroParcelasTotal;
    }

    public void setNumeroParcelasTotal(int numeroParcelasTotal) {
        this.numeroParcelasTotal = numeroParcelasTotal;
    }

    public int getParcela() {
        return parcela;
    }

    public void setParcela(int parcela) {
        this.parcela = parcela;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public CaminhaoModel getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(CaminhaoModel veiculo) {
        this.veiculo = veiculo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}