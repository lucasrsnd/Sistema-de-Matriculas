package com.TechFrotas.TechFrotas.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PedagioRecordDto(BigDecimal valor, String local, LocalDate data, String placaCaminhao) {
}