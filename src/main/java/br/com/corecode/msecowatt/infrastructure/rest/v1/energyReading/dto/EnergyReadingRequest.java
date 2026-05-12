package br.com.corecode.msecowatt.infrastructure.rest.v1.energyReading.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EnergyReadingRequest(
   @NotBlank String companyId,
   @NotNull LocalDate readingDate,
   @NotNull BigDecimal consumptionKwh
) {}
