package br.com.corecode.msecowatt.infrastructure.persistence.mongo.repository.user;

import br.com.corecode.msecowatt.infrastructure.persistence.mongo.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SpringDataUserMongoRepository extends MongoRepository<UserDocument, String> {

    Optional<UserDocument> findByUsername(String username);
    boolean existsByUsername(String username);
}
