package br.com.corecode.msecowatt.infrastructure.rest.v1.company.mapper;

import br.com.corecode.msecowatt.application.useCases.company.dto.CompanyOutput;
import br.com.corecode.msecowatt.application.useCases.company.dto.CreateCompanyInput;
import br.com.corecode.msecowatt.infrastructure.rest.v1.company.dto.CompanyRequest;
import br.com.corecode.msecowatt.infrastructure.rest.v1.company.dto.CompanyResponse;

public class CompanyRestMapper {
    public static CreateCompanyInput toInput(CompanyRequest request) {
        return new CreateCompanyInput(
                request.name(),
                request.cnpj(),
                request.phoneNumber(),
                request.street(),
                request.city(),
                request.state()
        );
    }

    public static CompanyResponse toResponse(CompanyOutput output) {
        return new CompanyResponse(
                output.id(),
                output.name(),
                output.cnpj()
        );
    }
}
