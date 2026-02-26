package br.com.corecode.msecowatt.domain.entity;

import br.com.corecode.msecowatt.domain.exceptions.InvalidDomainValueException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EnergyReading {
    private Long id;
    private final Long companyId;
    private final LocalDate readingDate;
    private final BigDecimal consumptionKwh;
    private BigDecimal emissionCo2;
    private final LocalDateTime createdAt;

    public EnergyReading(Long companyId, LocalDate readingDate, BigDecimal consumptionKwh) {
        if(companyId == null){
            throw new InvalidDomainValueException("companyId cannot be null");
        }

        if(consumptionKwh == null || consumptionKwh.compareTo(BigDecimal.ZERO) < 0){
            throw new InvalidDomainValueException("consumptionKwh cannot be negative");
        }

        this.companyId = companyId;
        this.consumptionKwh = consumptionKwh;
        this.readingDate = resolveDate(readingDate);
        this.createdAt = LocalDateTime.now();
    }

    private LocalDate resolveDate(LocalDate date) {
        LocalDate finalDate = (date == null) ? LocalDate.now() : date;
        if (finalDate.isAfter(LocalDate.now())) {
            throw new InvalidDomainValueException("Date cannot be after now.");
        }

        return finalDate;
    }

    public void calculateEmission(BigDecimal factor){
        this.emissionCo2 = factor.multiply(consumptionKwh);
    }

    public Long getId() {
        return id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public LocalDate getReadingDate() {
        return readingDate;
    }

    public BigDecimal getConsumptionKwh() {
        return consumptionKwh;
    }

    public BigDecimal getEmissionCo2() {
        return emissionCo2;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
