package br.com.corecode.msecowatt.application.useCases.company;

import br.com.corecode.msecowatt.application.exceptions.NotFoundException;
import br.com.corecode.msecowatt.application.useCases.company.dto.CompanyOutput;
import br.com.corecode.msecowatt.domain.entity.Company;
import br.com.corecode.msecowatt.domain.repository.CompanyRepository;

public class GetCompanyByCnpjUseCase {

    private final CompanyRepository repository;

    public GetCompanyByCnpjUseCase(CompanyRepository repository) {
        this.repository = repository;
    }

    public CompanyOutput execute(String cnpj) {
        Company company = repository.findByCnpj(cnpj).orElseThrow(() -> new NotFoundException("Company not found"));

        return new CompanyOutput(
                company.getId(),
                company.getName(),
                company.getCnpj().getValue()
        );
    }
}
