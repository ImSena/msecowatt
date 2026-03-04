package br.com.corecode.msecowatt.infrastructure.persistence.mongo.repository.alert;

import br.com.corecode.msecowatt.infrastructure.persistence.mongo.document.AlertDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SpringDataAlertMongoRepository extends MongoRepository<AlertDocument, String> {

    List<AlertDocument> findByCompanyId(String companyId);
}
