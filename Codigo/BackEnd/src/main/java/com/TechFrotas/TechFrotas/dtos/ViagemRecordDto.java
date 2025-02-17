package com.TechFrotas.TechFrotas.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ViagemRecordDto(String origem, String destino, LocalDate dataSaida, LocalDate dataChegada, BigDecimal valor, double pesoTransportado, String placaCaminhao) {


}
