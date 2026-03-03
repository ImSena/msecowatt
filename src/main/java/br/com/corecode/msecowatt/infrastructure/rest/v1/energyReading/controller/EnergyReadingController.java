package br.com.corecode.msecowatt.infrastructure.rest.v1.energyReading.controller;

import br.com.corecode.msecowatt.application.useCases.energyReading.*;
import br.com.corecode.msecowatt.infrastructure.annotations.ApiV1;
import br.com.corecode.msecowatt.infrastructure.rest.v1.energyReading.dto.EnergyReadingRequest;
import br.com.corecode.msecowatt.infrastructure.rest.v1.energyReading.dto.EnergyReadingResponse;
import br.com.corecode.msecowatt.infrastructure.rest.v1.energyReading.mapper.EnergyReadingRestMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ApiV1
@RequestMapping("/energy-readings")
public class EnergyReadingController {

    private final CreateEnergyReadingUseCase createUseCase;
    private final GetEnergyReadingUseCase getUseCase;
    private final ListEnergyReadingUseCase listUseCase;
    private final GetEnergyReadingByCompanyUseCase getByCompanyUseCase;
    private final GetEnergyReadingByPeriodUseCase getByPeriodUseCase;

    public EnergyReadingController(
            CreateEnergyReadingUseCase createUseCase,
            GetEnergyReadingUseCase getUseCase,
            ListEnergyReadingUseCase listUseCase,
            GetEnergyReadingByCompanyUseCase getByCompanyUseCase,
            GetEnergyReadingByPeriodUseCase getByPeriodUseCase
    ) {
        this.createUseCase = createUseCase;
        this.getUseCase = getUseCase;
        this.listUseCase = listUseCase;
        this.getByCompanyUseCase = getByCompanyUseCase;
        this.getByPeriodUseCase = getByPeriodUseCase;
    }

    @PostMapping
    public ResponseEntity<EnergyReadingResponse> create(
            @RequestBody EnergyReadingRequest request
    ) {
        var input = EnergyReadingRestMapper.toInput(request);
        var output = EnergyReadingRestMapper.toResponse(createUseCase.execute(input));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(output);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnergyReadingResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(
                EnergyReadingRestMapper.toResponse(getUseCase.execute(id))
        );
    }

    @GetMapping
    public ResponseEntity<List<EnergyReadingResponse>> findAll() {
        List<EnergyReadingResponse> response = listUseCase.findAll()
                .stream()
                .map(EnergyReadingRestMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<EnergyReadingResponse>> getByCompany(
            @PathVariable String companyId
    ) {
        List<EnergyReadingResponse> response = getByCompanyUseCase.execute(companyId)
                .stream()
                .map(EnergyReadingRestMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/period")
    public ResponseEntity<List<EnergyReadingResponse>> getByPeriod(
            @RequestParam String companyId,
            @RequestParam String start,
            @RequestParam String end
    ) {
        var response = getByPeriodUseCase.execute(
                companyId,
                java.time.LocalDate.parse(start),
                java.time.LocalDate.parse(end)
        ).stream().map(EnergyReadingRestMapper::toResponse).toList();

        return ResponseEntity.ok(response);
    }
}
