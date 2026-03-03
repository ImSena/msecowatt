package br.com.corecode.msecowatt.application.useCases.company;

import br.com.corecode.msecowatt.application.exceptions.NotFoundException;
import br.com.corecode.msecowatt.domain.entity.Company;
import br.com.corecode.msecowatt.domain.repository.CompanyRepository;

public class DeleteCompanyUseCase {

    private final CompanyRepository repository;

    public DeleteCompanyUseCase(CompanyRepository repository) {
        this.repository = repository;
    }

    public void execute(String id) {
        Company company = repository.findById(id).orElseThrow(() -> new NotFoundException("Company not found"));

        repository.delete(company.getId());
    }
}
