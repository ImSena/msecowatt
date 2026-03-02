package br.com.corecode.msecowatt.domain.repository;

import br.com.corecode.msecowatt.domain.entity.EnergyReading;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EnergyReadingRepository {

    EnergyReading save(EnergyReading energyReading);
    Optional<EnergyReading> findById(String id);
    List<EnergyReading> findAll();
    void delete(String id);

    List<EnergyReading> findByCompanyId(String comapanyId);
    List<EnergyReading> findByPeriod(String companyId, LocalDate start, LocalDate end);

}
