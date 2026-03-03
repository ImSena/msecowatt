package br.com.corecode.msecowatt.infrastructure.advice;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        int status,
        String message,
        LocalDateTime timestamp,
        List<ValidationError> errors
) {
    public record ValidationError(String field, String message) {}
}
