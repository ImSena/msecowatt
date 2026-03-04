package br.com.corecode.msecowatt.infrastructure.rest.v1.alert.mapper;

import br.com.corecode.msecowatt.application.useCases.alert.dto.AlertInput;
import br.com.corecode.msecowatt.application.useCases.alert.dto.AlertOutput;
import br.com.corecode.msecowatt.infrastructure.rest.v1.alert.dto.AlertRequest;
import br.com.corecode.msecowatt.infrastructure.rest.v1.alert.dto.AlertResponse;

public class AlertRestMapper {
    public static AlertInput toInput(AlertRequest alertRequest) {
        return new AlertInput(
                alertRequest.companyId(), alertRequest.type(), alertRequest.message()
        );
    }

    public static AlertResponse toOutput(AlertOutput alertOutput) {
        return new AlertResponse(
                alertOutput.id(),
                alertOutput.companyId(),
                alertOutput.type(),
                alertOutput.message(),
                alertOutput.resolved()
        );
    }
}
