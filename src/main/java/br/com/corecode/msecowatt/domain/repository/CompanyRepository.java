package br.com.corecode.msecowatt.domain.repository;

import br.com.corecode.msecowatt.domain.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository {

    Company save(Company company);
    Optional<Company> findById(Long id);
    List<Company> findAll();
    void delete(Long id);
}
