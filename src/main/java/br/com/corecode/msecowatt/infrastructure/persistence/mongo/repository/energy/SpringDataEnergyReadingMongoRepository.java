package br.com.corecode.msecowatt.infrastructure.persistence.mongo.repository.energy;

import br.com.corecode.msecowatt.infrastructure.persistence.mongo.document.EnergyReadingDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface SpringDataEnergyReadingMongoRepository extends MongoRepository<EnergyReadingDocument, String> {
    List<EnergyReadingDocument> findByCompanyId(String companyId);
    List<EnergyReadingDocument> findByCompanyIdAndReadingDateBetween(String companyId, LocalDate start, LocalDate end);
}
