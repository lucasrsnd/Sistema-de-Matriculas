package com.TechFrotas.TechFrotas.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ABASTECIMENTOS") // Nome da tabela no banco de dados
public class AbastecimentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal valor; // Use BigDecimal para valores monetários

    @Column(nullable = false)
    private Integer litros;

    @Column(nullable = false)
    private String local;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private Integer km;

    @ManyToOne // Relacionamento muitos-para-um com CaminhaoModel
    @JoinColumn(name = "caminhao_id", nullable = false) // Coluna para a chave estrangeira
    private CaminhaoModel caminhao;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getLitros() {
        return litros;
    }

    public void setLitros(Integer litros) {
        this.litros = litros;
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

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
    }

    public CaminhaoModel getCaminhao() {
        return caminhao;
    }

    public void setCaminhao(CaminhaoModel caminhao) {
        this.caminhao = caminhao;
    }
}