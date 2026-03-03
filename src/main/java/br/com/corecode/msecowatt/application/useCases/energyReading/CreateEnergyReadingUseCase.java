package br.com.corecode.msecowatt.application.useCases.energyReading;

import br.com.corecode.msecowatt.application.exceptions.NotFoundException;
import br.com.corecode.msecowatt.application.useCases.energyReading.dto.CreateEnergyReadingInput;
import br.com.corecode.msecowatt.application.useCases.energyReading.dto.EnergyReadingOutput;
import br.com.corecode.msecowatt.domain.entity.EnergyReading;
import br.com.corecode.msecowatt.domain.repository.CompanyRepository;
import br.com.corecode.msecowatt.domain.repository.EnergyReadingRepository;

import java.math.BigDecimal;

public class CreateEnergyReadingUseCase {

    private final EnergyReadingRepository repository;
    private final CompanyRepository companyRepository;
    private static final BigDecimal EMISSION_FACTOR = new BigDecimal("0.084");

    public CreateEnergyReadingUseCase(EnergyReadingRepository repository, CompanyRepository companyRepository) {
        this.repository = repository;
        this.companyRepository = companyRepository;
    }


    public EnergyReadingOutput execute(CreateEnergyReadingInput input) {
        companyRepository.findById(input.companyId())
                .orElseThrow(() -> new NotFoundException("Company not found"));

        EnergyReading energy = new EnergyReading(
                input.companyId(),
                input.readingDate(),
                input.consumptionKwh()
        );

        energy.calculateEmission(EMISSION_FACTOR);

        EnergyReading saved = repository.save(energy);

        return new EnergyReadingOutput(
                saved.getId(),
                saved.getCompanyId(),
                saved.getReadingDate(),
                saved.getConsumptionKwh(),
                saved.getEmissionCo2()
        );
    }

}
