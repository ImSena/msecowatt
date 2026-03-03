package br.com.corecode.msecowatt.application.useCases.company;

import br.com.corecode.msecowatt.application.exceptions.NotFoundException;
import br.com.corecode.msecowatt.application.useCases.company.dto.CompanyOutput;
import br.com.corecode.msecowatt.domain.entity.Company;
import br.com.corecode.msecowatt.domain.repository.CompanyRepository;

public class GetCompanyUseCase {

    private final CompanyRepository repository;

    public GetCompanyUseCase(CompanyRepository repository) {
        this.repository = repository;
    }

    public CompanyOutput execute(String id) {
        Company company = repository.findById(id).orElseThrow(() -> new NotFoundException("Company not found"));

        return new CompanyOutput(
                company.getId(),
                company.getName(),
                company.getCnpj().getValue()
        );
    }
}
