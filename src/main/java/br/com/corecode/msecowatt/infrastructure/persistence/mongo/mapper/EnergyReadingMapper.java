package br.com.corecode.msecowatt.infrastructure.persistence.mongo.mapper;

import br.com.corecode.msecowatt.domain.entity.EnergyReading;
import br.com.corecode.msecowatt.infrastructure.persistence.mongo.document.EnergyReadingDocument;

public class EnergyReadingMapper {

    public static EnergyReadingDocument toDocument(EnergyReading entity){
        return new EnergyReadingDocument(
                entity.getId(),
                entity.getCompanyId(),
                entity.getReadingDate(),
                entity.getConsumptionKwh(),
                entity.getEmissionCo2(),
                entity.getCreatedAt()
        );
    }

    public static EnergyReading toEntity(EnergyReadingDocument document){
        return EnergyReading.restore(
            document.getId(),
            document.getCompanyId(),
            document.getReadingDate(),
            document.getConsumptionKwh(),
            document.getEmissionCo2(),
            document.getCreatedAt()
        );
    }
}
