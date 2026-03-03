package br.com.corecode.msecowatt.application.useCases.energyReading;

import br.com.corecode.msecowatt.application.exceptions.NotFoundException;
import br.com.corecode.msecowatt.application.useCases.energyReading.dto.EnergyReadingOutput;
import br.com.corecode.msecowatt.domain.repository.CompanyRepository;
import br.com.corecode.msecowatt.domain.repository.EnergyReadingRepository;

import java.util.List;

public class GetEnergyReadingByCompanyUseCase {

    private final EnergyReadingRepository energyRepository;
    private final CompanyRepository companyRepository;

    public GetEnergyReadingByCompanyUseCase(EnergyReadingRepository energyRepository, CompanyRepository companyRepository) {
        this.energyRepository = energyRepository;
        this.companyRepository = companyRepository;
    }

    public List<EnergyReadingOutput> execute(String companyId) {
        companyRepository.findById(companyId).orElseThrow(() -> new NotFoundException("Company not found"));

        return energyRepository.findByCompanyId(companyId)
                .stream()
                .map(energyReading -> new EnergyReadingOutput(
                   energyReading.getId(),
                   energyReading.getCompanyId(),
                   energyReading.getReadingDate(),
                   energyReading.getConsumptionKwh(),
                   energyReading.getEmissionCo2()
                ))
                .toList();
    }

}
