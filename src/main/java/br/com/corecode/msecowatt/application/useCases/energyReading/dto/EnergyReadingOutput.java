package br.com.corecode.msecowatt.application.useCases.energyReading.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EnergyReadingOutput(
        String id,
        String companyId,
        LocalDate readingDate,
        BigDecimal consumptionKwh,
        BigDecimal emissionCo2
) {
}
