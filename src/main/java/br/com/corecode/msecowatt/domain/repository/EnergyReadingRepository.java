package br.com.corecode.msecowatt.domain.repository;

import br.com.corecode.msecowatt.domain.entity.EnergyReading;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EnergyReadingRepository {

    EnergyReading save(EnergyReading energyReading);
    Optional<EnergyReading> findById(Long id);
    List<EnergyReading> findAll();
    void delete(EnergyReading energyReading);

    List<EnergyReading> findByCompanyId(String comapanyId);
    List<EnergyReading> findByPeriod(Long companyId, LocalDate start, LocalDate end);

}
