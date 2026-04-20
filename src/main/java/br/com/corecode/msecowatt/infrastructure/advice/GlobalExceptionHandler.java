package br.com.corecode.msecowatt.infrastructure.advice;

import br.com.corecode.msecowatt.application.exceptions.AlreadyExistsRegisterException;
import br.com.corecode.msecowatt.application.exceptions.NotFoundException;
import br.com.corecode.msecowatt.application.exceptions.StartDateAfterException;
import br.com.corecode.msecowatt.domain.exceptions.DomainException;
import br.com.corecode.msecowatt.domain.exceptions.InvalidDomainValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(AlreadyExistsRegisterException.class)
    public ResponseEntity<ErrorResponse> handleConflict(AlreadyExistsRegisterException ex) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler({
            DomainException.class,
            StartDateAfterException.class,
            InvalidDomainValueException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequest(RuntimeException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        List<ErrorResponse.ValidationError> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new ErrorResponse.ValidationError(err.getField(), err.getDefaultMessage()))
                .toList();

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Error Validation Failed on field",
                LocalDateTime.now(),
                errors
        );
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException ex) {
        return buildResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String message) {
        ErrorResponse error = new ErrorResponse(status.value(), message, LocalDateTime.now(), null);
        return ResponseEntity.status(status).body(error);
    }
}
