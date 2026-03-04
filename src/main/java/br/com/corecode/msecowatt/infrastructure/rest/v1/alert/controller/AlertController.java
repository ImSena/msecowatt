package br.com.corecode.msecowatt.infrastructure.rest.v1.alert.controller;

import br.com.corecode.msecowatt.application.useCases.alert.CreateAlertUseCase;
import br.com.corecode.msecowatt.infrastructure.annotations.ApiV1;
import br.com.corecode.msecowatt.infrastructure.rest.v1.alert.dto.AlertRequest;
import br.com.corecode.msecowatt.infrastructure.rest.v1.alert.dto.AlertResponse;
import br.com.corecode.msecowatt.infrastructure.rest.v1.alert.mapper.AlertRestMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiV1
@RequestMapping("/alerts")
public class AlertController {

    private final CreateAlertUseCase createAlertUseCase;

    public AlertController(CreateAlertUseCase createAlertUseCase) {
        this.createAlertUseCase = createAlertUseCase;
    }

    @PostMapping
    public ResponseEntity<AlertResponse> create(@RequestBody AlertRequest request) {
        var input = AlertRestMapper.toInput(request);
        var output = AlertRestMapper.toOutput(createAlertUseCase.execute(input));

        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }

}
