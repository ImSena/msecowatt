package br.com.corecode.msecowatt.infrastructure.persistence.mongo.repository.alert;

import br.com.corecode.msecowatt.domain.entity.Alert;
import br.com.corecode.msecowatt.domain.repository.AlertRepository;
import br.com.corecode.msecowatt.infrastructure.persistence.mongo.mapper.AlertMongoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlertMongoRepositoryAdapter implements AlertRepository {

    private final SpringDataAlertMongoRepository repository;
    private final AlertMongoMapper mapper;

    public AlertMongoRepositoryAdapter(SpringDataAlertMongoRepository repository, AlertMongoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Alert save(Alert alert) {
        return mapper.toDomain(
                repository.save(mapper.toDocument(alert))
        );
    }

    @Override
    public Optional<Alert> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Alert> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Alert> findByCompanyId(String companyId) {
        return repository.findByCompanyId(companyId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
