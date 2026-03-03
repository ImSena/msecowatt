package br.com.corecode.msecowatt.application.useCases.energyReading;

import br.com.corecode.msecowatt.application.useCases.energyReading.dto.EnergyReadingOutput;
import br.com.corecode.msecowatt.domain.entity.EnergyReading;
import br.com.corecode.msecowatt.domain.repository.EnergyReadingRepository;

import java.util.List;

public class ListEnergyReadingUseCase {
    private final EnergyReadingRepository repository;

    public ListEnergyReadingUseCase(EnergyReadingRepository repository) {
        this.repository = repository;
    }

    public List<EnergyReadingOutput> findAll() {
        return repository.findAll()
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
