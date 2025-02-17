package com.TechFrotas.TechFrotas.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pedagios")
public class PedagioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal valor; // Usando BigDecimal para valores monetários

    @Column(nullable = false)
    private String local;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "caminhao_id", nullable = false)
    private CaminhaoModel caminhao;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CaminhaoModel getCaminhao() {
        return caminhao;
    }

    public void setCaminhao(CaminhaoModel caminhao) {
        this.caminhao = caminhao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}