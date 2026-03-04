package br.com.corecode.msecowatt.application.useCases.alert;

import br.com.corecode.msecowatt.application.exceptions.NotFoundException;
import br.com.corecode.msecowatt.application.useCases.alert.dto.AlertInput;
import br.com.corecode.msecowatt.application.useCases.alert.dto.AlertOutput;
import br.com.corecode.msecowatt.domain.entity.Alert;
import br.com.corecode.msecowatt.domain.repository.AlertRepository;
import br.com.corecode.msecowatt.domain.repository.CompanyRepository;

public class CreateAlertUseCase {

    private final AlertRepository alertRepository;
    private final CompanyRepository companyRepository;

    public CreateAlertUseCase(AlertRepository alertRepository, CompanyRepository companyRepository) {
        this.alertRepository = alertRepository;
        this.companyRepository = companyRepository;
    }

    public AlertOutput execute(AlertInput input) {
        companyRepository.findById(input.companyId()).orElseThrow(() -> new NotFoundException("Company not found"));

        Alert alert = new Alert(input.companyId(), input.type(), input.message());



        Alert alertCreated = alertRepository.save(alert);

        return new AlertOutput(
                alertCreated.getId(),
                alertCreated.getCompanyId(),
                alertCreated.getType(),
                alertCreated.getMessage(),
                alertCreated.isResolved()
        );
    }
}
