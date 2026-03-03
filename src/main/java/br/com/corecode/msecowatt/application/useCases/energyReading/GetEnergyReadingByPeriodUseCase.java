package br.com.corecode.msecowatt.application.useCases.energyReading;

import br.com.corecode.msecowatt.application.exceptions.NotFoundException;
import br.com.corecode.msecowatt.application.exceptions.StartDateAfterException;
import br.com.corecode.msecowatt.application.useCases.energyReading.dto.EnergyReadingOutput;
import br.com.corecode.msecowatt.domain.repository.CompanyRepository;
import br.com.corecode.msecowatt.domain.repository.EnergyReadingRepository;

import java.time.LocalDate;
import java.util.List;

public class GetEnergyReadingByPeriodUseCase {

    private final EnergyReadingRepository energyReadingRepository;
    private final CompanyRepository companyRepository;

    public GetEnergyReadingByPeriodUseCase(EnergyReadingRepository energyReadingRepository, CompanyRepository companyRepository) {
        this.energyReadingRepository = energyReadingRepository;
        this.companyRepository = companyRepository;

    }

    public List<EnergyReadingOutput> execute(String companyId, LocalDate start, LocalDate end) {
        companyRepository.findById(companyId).orElseThrow(() -> new NotFoundException("Company not found"));

        if(start.isAfter(end)) {
            throw new StartDateAfterException("Start date cannot be after end date");
        }

        return energyReadingRepository.findByPeriod(companyId, start, end)
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
