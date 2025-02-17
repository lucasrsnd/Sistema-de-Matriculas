package com.TechFrotas.TechFrotas.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "VIAGENS")
public class ViagemModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String origem;

    @Column(nullable = false)
    private String destino;

    @Column(nullable = false)
    private LocalDate dataSaida;

    @Column(nullable = false)
    private LocalDate dataChegada;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private double pesoTransportado;

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

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public LocalDate getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(LocalDate dataChegada) {
        this.dataChegada = dataChegada;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public double getPesoTransportado() {
        return pesoTransportado;
    }

    public void setPesoTransportado(double pesoTransportado) {
        this.pesoTransportado = pesoTransportado;
    }

    public CaminhaoModel getCaminhao() {
        return caminhao;
    }

    public void setCaminhao(CaminhaoModel caminhao) {
        this.caminhao = caminhao;
    }
}
