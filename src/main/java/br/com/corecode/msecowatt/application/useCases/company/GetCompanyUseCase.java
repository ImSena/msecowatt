package br.com.corecode.msecowatt.application.useCases.company;

import br.com.corecode.msecowatt.application.exceptions.NotFound;
import br.com.corecode.msecowatt.application.useCases.company.dto.CompanyOutput;
import br.com.corecode.msecowatt.domain.entity.Company;
import br.com.corecode.msecowatt.domain.repository.CompanyRepository;

public class GetCompanyUseCase {

    private final CompanyRepository repository;

    public GetCompanyUseCase(CompanyRepository repository) {
        this.repository = repository;
    }

    public CompanyOutput execute(String id) {
        Company company = repository.findById(id).orElseThrow(() -> new NotFound("Company not found"));

        return new CompanyOutput(
                company.getId(),
                company.getName(),
                company.getCnpj().getValue()
        );
    }
}
