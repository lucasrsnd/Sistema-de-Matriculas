package com.TechFrotas.TechFrotas.dtos;

import java.math.BigDecimal;

public record GastosVeiculosRecordDto(String placaCaminhao, String tipo, BigDecimal valor, int parcela, int numeroParcelasTotal) {
}
