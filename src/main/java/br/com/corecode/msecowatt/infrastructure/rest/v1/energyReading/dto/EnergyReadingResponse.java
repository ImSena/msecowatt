package br.com.corecode.msecowatt.infrastructure.rest.v1.energyReading.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EnergyReadingResponse(
        String id,
        String companyId,
        LocalDate readingDate,
        BigDecimal consumptionKwh,
        BigDecimal emissionCo2
) {
}
