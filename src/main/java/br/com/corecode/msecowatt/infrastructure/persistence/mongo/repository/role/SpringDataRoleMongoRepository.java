package br.com.corecode.msecowatt.infrastructure.persistence.mongo.repository.role;

import br.com.corecode.msecowatt.infrastructure.persistence.mongo.document.RoleDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SpringDataRoleMongoRepository extends MongoRepository<RoleDocument, String> {
    Optional<RoleDocument> findByName(String roleName);
}
