package br.com.corecode.msecowatt.infrastructure.persistence.mongo.repository.role;

import br.com.corecode.msecowatt.domain.entity.Role;
import br.com.corecode.msecowatt.domain.repository.RoleRepository;
import br.com.corecode.msecowatt.infrastructure.persistence.mongo.mapper.RoleMongoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleMongoRepositoryAdapter implements RoleRepository {

    private final SpringDataRoleMongoRepository repository;
    private final RoleMongoMapper mapper;

    public RoleMongoRepositoryAdapter(SpringDataRoleMongoRepository repository, RoleMongoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Role> findById(String id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return repository.findByName(name)
                .map(mapper::toDomain);
    }

    @Override
    public List<Role> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Role save(Role role) {
        return mapper.toDomain(
                repository.save(mapper.toDocument(role))
        );
    }
}
