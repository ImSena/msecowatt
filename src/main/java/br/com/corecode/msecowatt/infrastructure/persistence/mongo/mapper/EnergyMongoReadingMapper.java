package br.com.corecode.msecowatt.infrastructure.persistence.mongo.mapper;

import br.com.corecode.msecowatt.domain.entity.EnergyReading;
import br.com.corecode.msecowatt.infrastructure.persistence.mongo.document.EnergyReadingDocument;
import org.springframework.stereotype.Component;

@Component
public class EnergyMongoReadingMapper {

    public EnergyReadingDocument toDocument(EnergyReading entity){
        return new EnergyReadingDocument(
                entity.getId(),
                entity.getCompanyId(),
                entity.getReadingDate(),
                entity.getConsumptionKwh(),
                entity.getEmissionCo2(),
                entity.getCreatedAt()
        );
    }

    public EnergyReading toDomain(EnergyReadingDocument document){
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
