package br.com.corecode.msecowatt.infrastructure.persistence.mongo.repository.energy;

import br.com.corecode.msecowatt.domain.entity.EnergyReading;
import br.com.corecode.msecowatt.domain.repository.EnergyReadingRepository;
import br.com.corecode.msecowatt.infrastructure.persistence.mongo.mapper.EnergyMongoReadingMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class EnergyReadingMongoRepositoryAdapter implements EnergyReadingRepository {

    private final SpringDataEnergyReadingMongoRepository repository;
    private final EnergyMongoReadingMapper mapper;

    public EnergyReadingMongoRepositoryAdapter(SpringDataEnergyReadingMongoRepository repository, EnergyMongoReadingMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public EnergyReading save(EnergyReading energyReading) {
        return mapper.toDomain(
                repository.save(mapper.toDocument(energyReading))
        );
    }

    @Override
    public Optional<EnergyReading> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<EnergyReading> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<EnergyReading> findByCompanyId(String comapanyId) {
        return repository.findByCompanyId(comapanyId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<EnergyReading> findByPeriod(String companyId, LocalDate start, LocalDate end) {
        return repository.findByCompanyIdAndReadingDateBetween(companyId, start, end)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
