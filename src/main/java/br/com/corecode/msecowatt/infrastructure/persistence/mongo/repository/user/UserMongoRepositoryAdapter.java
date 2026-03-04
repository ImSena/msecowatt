package br.com.corecode.msecowatt.infrastructure.persistence.mongo.repository.user;

import br.com.corecode.msecowatt.domain.entity.User;
import br.com.corecode.msecowatt.domain.repository.UserRepository;
import br.com.corecode.msecowatt.infrastructure.persistence.mongo.mapper.UserMongoMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserMongoRepositoryAdapter implements UserRepository {

    private final SpringDataUserMongoRepository repository;
    private final UserMongoMapper mapper;

    public UserMongoRepositoryAdapter(SpringDataUserMongoRepository repository, UserMongoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {
        return mapper.toDomain(
                repository.save(mapper.toDocument(user))
        );
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<User> findById(String id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
