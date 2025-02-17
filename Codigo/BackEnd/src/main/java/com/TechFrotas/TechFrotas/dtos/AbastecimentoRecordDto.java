package com.TechFrotas.TechFrotas.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AbastecimentoRecordDto(BigDecimal valor, Integer litros, String local, LocalDate data, Integer km, String placaCaminhao) {
}