package br.com.corecode.msecowatt.domain.repository;

import br.com.corecode.msecowatt.domain.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository {

    Company save(Company company);
    Optional<Company> findById(String id);
    List<Company> findAll();
    void delete(String id);
}
