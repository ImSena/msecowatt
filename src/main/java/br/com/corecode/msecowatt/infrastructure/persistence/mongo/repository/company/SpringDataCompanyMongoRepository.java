package br.com.corecode.msecowatt.infrastructure.persistence.mongo.repository.company;

import br.com.corecode.msecowatt.infrastructure.persistence.mongo.document.CompanyDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SpringDataCompanyMongoRepository extends MongoRepository<CompanyDocument, String> {
    Optional<CompanyDocument> findByCnpj(String cnpj);
}
