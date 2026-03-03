package br.com.corecode.msecowatt.infrastructure.rest.v1.company.controller;

import br.com.corecode.msecowatt.application.useCases.company.*;
import br.com.corecode.msecowatt.infrastructure.annotations.ApiV1;
import br.com.corecode.msecowatt.infrastructure.rest.v1.company.dto.CompanyRequest;
import br.com.corecode.msecowatt.infrastructure.rest.v1.company.dto.CompanyResponse;
import br.com.corecode.msecowatt.infrastructure.rest.v1.company.mapper.CompanyRestMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ApiV1
@RequestMapping("/companies")
public class CompanyController {
    private final CreateCompanyUseCase createUseCase;
    private final GetCompanyUseCase getUseCase;
    private final ListCompaniesUseCase listUseCase;
    private final DeleteCompanyUseCase deleteUseCase;
    private final GetCompanyByCnpjUseCase getCompanyByCnpjUseCase;

    public CompanyController(
            CreateCompanyUseCase createUseCase,
            GetCompanyUseCase getUseCase,
            ListCompaniesUseCase listUseCase,
            DeleteCompanyUseCase deleteUseCase,
            GetCompanyByCnpjUseCase getCompanyByCnpjUseCase
    ) {
        this.createUseCase = createUseCase;
        this.getUseCase = getUseCase;
        this.listUseCase = listUseCase;
        this.deleteUseCase = deleteUseCase;
        this.getCompanyByCnpjUseCase = getCompanyByCnpjUseCase;
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> create(@RequestBody @Valid CompanyRequest request) {
        var input = CompanyRestMapper.toInput(request);
        var output = CompanyRestMapper.toResponse(createUseCase.execute(input));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(output);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getById(@PathVariable String id) {
        var output = CompanyRestMapper.toResponse(
                getUseCase.execute(id)
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(output);
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAll() {
        var output = listUseCase.execute()
                .stream()
                .map(CompanyRestMapper::toResponse)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(output);
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<CompanyResponse> getByCnpj(@PathVariable String cnpj) {
        var output = CompanyRestMapper.toResponse(
                getCompanyByCnpjUseCase.execute(cnpj)
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        deleteUseCase.execute(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
