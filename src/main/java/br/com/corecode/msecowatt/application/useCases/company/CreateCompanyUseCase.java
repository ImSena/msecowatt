package br.com.corecode.msecowatt.application.useCases.company;

import br.com.corecode.msecowatt.application.exceptions.AlreadyExistsRegisterException;
import br.com.corecode.msecowatt.application.useCases.company.dto.CompanyOutput;
import br.com.corecode.msecowatt.application.useCases.company.dto.CreateCompanyInput;
import br.com.corecode.msecowatt.domain.entity.Company;
import br.com.corecode.msecowatt.domain.repository.CompanyRepository;
import br.com.corecode.msecowatt.domain.valueObject.Cnpj;
import br.com.corecode.msecowatt.domain.valueObject.Location;

public class CreateCompanyUseCase {

    private final CompanyRepository repository;

    public CreateCompanyUseCase(CompanyRepository repository) {
        this.repository = repository;
    }

    public CompanyOutput execute(CreateCompanyInput input) {
        repository.findByCnpj(input.cnpj())
                .ifPresent(company -> {
                    throw new AlreadyExistsRegisterException("Company with CNPJ already exists");
                });

        Cnpj cnpj = new Cnpj(input.cnpj());
        Location location = new Location(input.street(), input.city(), input.state());

        Company company = new Company(
                null,
                input.name(),
                cnpj,
                location,
                input.phoneNumber()
        );

        Company createdCompany = repository.save(company);

        return new CompanyOutput(
                createdCompany.getId(),
                createdCompany.getName(),
                createdCompany.getCnpj().getValue()
        );

    }


}
