package br.com.corecode.msecowatt.infrastructure.persistence.mongo.repository.company;

import br.com.corecode.msecowatt.domain.entity.Company;
import br.com.corecode.msecowatt.domain.repository.CompanyRepository;
import br.com.corecode.msecowatt.infrastructure.persistence.mongo.mapper.CompanyMongoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompanyMongoRepositoryAdapter implements CompanyRepository {

    private final SpringDataCompanyMongoRepository repository;
    private final CompanyMongoMapper mapper;

    public CompanyMongoRepositoryAdapter(SpringDataCompanyMongoRepository repository, CompanyMongoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Company save(Company company) {
        return mapper.toDomain(
                repository.save(mapper.toDocument(company))
        );
    }

    @Override
    public Optional<Company> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Company> findByCnpj(String cnpj) {
        return repository.findByCnpj(cnpj).map(mapper::toDomain);
    }

    @Override
    public List<Company> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
