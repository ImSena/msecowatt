package br.com.corecode.msecowatt.infrastructure.persistence.mongo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "energy_readings")
public class EnergyReadingDocument {
    @Id
    private String id;

    private String companyId;
    private LocalDate readingDate;
    private BigDecimal consumptionKwh;
    private BigDecimal emissionCo2;
    private LocalDateTime createdAt;

    public EnergyReadingDocument() {}

    public EnergyReadingDocument(
            String id,
            String companyId,
            LocalDate readingDate,
            BigDecimal consumptionKwh,
            BigDecimal emissionCo2,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.companyId = companyId;
        this.readingDate = readingDate;
        this.consumptionKwh = consumptionKwh;
        this.emissionCo2 = emissionCo2;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getCompanyId() {
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

    public void setId(String id) {
        this.id = id;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setReadingDate(LocalDate readingDate) {
        this.readingDate = readingDate;
    }

    public void setConsumptionKwh(BigDecimal consumptionKwh) {
        this.consumptionKwh = consumptionKwh;
    }

    public void setEmissionCo2(BigDecimal emissionCo2) {
        this.emissionCo2 = emissionCo2;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
