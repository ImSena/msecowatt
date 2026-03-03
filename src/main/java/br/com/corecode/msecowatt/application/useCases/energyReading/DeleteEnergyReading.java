package br.com.corecode.msecowatt.application.useCases.energyReading;

import br.com.corecode.msecowatt.application.exceptions.NotFoundException;
import br.com.corecode.msecowatt.domain.entity.EnergyReading;
import br.com.corecode.msecowatt.domain.repository.EnergyReadingRepository;

public class DeleteEnergyReading {
    private final EnergyReadingRepository repository;
    public DeleteEnergyReading(EnergyReadingRepository repository) {
        this.repository = repository;
    }

    public void delete(String id) {
        repository.findById(id).orElseThrow(()-> new NotFoundException("EnergyReading not found"));

        repository.delete(id);
    }
}
