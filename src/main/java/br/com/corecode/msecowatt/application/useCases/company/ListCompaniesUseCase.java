package br.com.corecode.msecowatt.application.useCases.company;

import br.com.corecode.msecowatt.application.useCases.company.dto.CompanyOutput;
import br.com.corecode.msecowatt.domain.entity.Company;
import br.com.corecode.msecowatt.domain.repository.CompanyRepository;

import java.util.List;

public class ListCompaniesUseCase {
    private final CompanyRepository repository;

    public ListCompaniesUseCase(CompanyRepository repository) {
        this.repository = repository;
    }

    public List<CompanyOutput> execute() {
        List<CompanyOutput> companies = repository.findAll()
                .stream()
                .map(company -> new CompanyOutput(company.getId(), company.getName(), company.getCnpj().getValue()))
                .toList()
        ;

        return companies;
    }
}
