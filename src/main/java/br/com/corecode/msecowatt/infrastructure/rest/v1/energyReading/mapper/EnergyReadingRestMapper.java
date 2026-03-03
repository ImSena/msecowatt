package br.com.corecode.msecowatt.infrastructure.rest.v1.energyReading.mapper;

import br.com.corecode.msecowatt.application.useCases.energyReading.dto.CreateEnergyReadingInput;
import br.com.corecode.msecowatt.application.useCases.energyReading.dto.EnergyReadingOutput;
import br.com.corecode.msecowatt.infrastructure.rest.v1.energyReading.dto.EnergyReadingRequest;
import br.com.corecode.msecowatt.infrastructure.rest.v1.energyReading.dto.EnergyReadingResponse;

public class EnergyReadingRestMapper {

    public static CreateEnergyReadingInput toInput(EnergyReadingRequest request) {
        return new CreateEnergyReadingInput(
                request.companyId(),
                request.readingDate(),
                request.consumptionKwh()
        );
    }

    public static EnergyReadingResponse toResponse(EnergyReadingOutput output) {
        return new EnergyReadingResponse(
                output.id(),
                output.companyId(),
                output.readingDate(),
                output.consumptionKwh(),
                output.emissionCo2()
        );
    }
}
