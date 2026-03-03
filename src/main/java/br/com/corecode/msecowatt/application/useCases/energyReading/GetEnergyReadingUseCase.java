package br.com.corecode.msecowatt.application.useCases.energyReading;

import br.com.corecode.msecowatt.application.exceptions.NotFoundException;
import br.com.corecode.msecowatt.application.useCases.energyReading.dto.EnergyReadingOutput;
import br.com.corecode.msecowatt.domain.entity.EnergyReading;
import br.com.corecode.msecowatt.domain.repository.EnergyReadingRepository;

public class GetEnergyReadingUseCase {

    private final EnergyReadingRepository repository;

    public GetEnergyReadingUseCase(EnergyReadingRepository repository) {
        this.repository = repository;
    }

    public EnergyReadingOutput execute(String id) {
        EnergyReading energy =  repository.findById(id).orElseThrow(()-> new NotFoundException("EnergyReading not found"));

        return new EnergyReadingOutput(
                energy.getId(),
                energy.getCompanyId(),
                energy.getReadingDate(),
                energy.getConsumptionKwh(),
                energy.getEmissionCo2()
        ) ;
    }

}
