package br.com.corecode.msecowatt.domain.repository;

import br.com.corecode.msecowatt.domain.entity.Alert;

import java.util.List;
import java.util.Optional;

public interface AlertRepository {

    Alert save(Alert alert);

    Optional<Alert> findById(String id);

    List<Alert> findAll();

    List<Alert> findByCompanyId(String companyId);

    void deleteById(String id);
}
