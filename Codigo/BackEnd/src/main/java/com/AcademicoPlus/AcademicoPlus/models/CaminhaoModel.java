package com.TechFrotas.TechFrotas.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "CAMINHOES")
public class CaminhaoModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String placa;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private int ano;

    @OneToOne(optional = true)
    private FuncionarioModel funcionario;

    @Column(nullable = false)
    private int km;

    @Column(nullable = false)
    private int kmTrocaOleo;

    @Column(nullable = false)
    private int ultimaTrocaOleoKm;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getKmTrocaOleo() {
        return kmTrocaOleo;
    }

    public void setKmTrocaOleo(int kmTrocaOleo){
        this.kmTrocaOleo = kmTrocaOleo;
    }

    public int getUltimaTrocaOleoKm() {
        return ultimaTrocaOleoKm;
    }

    public void setUltimaTrocaOleoKm(int ultimaTrocaOleoKm){
        this.ultimaTrocaOleoKm = ultimaTrocaOleoKm;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km){
        this.km = km;
    }

    public FuncionarioModel getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioModel funcionario) {
        this.funcionario = funcionario;
    }


}
