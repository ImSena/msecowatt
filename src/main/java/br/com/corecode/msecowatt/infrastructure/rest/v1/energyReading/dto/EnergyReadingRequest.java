package br.com.corecode.msecowatt.infrastructure.rest.v1.energyReading.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EnergyReadingRequest(
   @NotBlank String companyId,
   @NotBlank LocalDate readingDate,
   @NotBlank BigDecimal consumptionKwh
) {}
