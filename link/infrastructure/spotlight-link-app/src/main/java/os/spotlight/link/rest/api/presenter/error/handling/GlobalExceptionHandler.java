package os.spotlight.link.rest.api.presenter.error.handling;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import os.spotlight.link.rest.api.gateway.exception.DeserializationException;
import os.spotlight.link.rest.api.gateway.exception.RestCallException;
import os.spotlight.link.rest.api.gateway.exception.RestResponseException;
import os.spotlight.link.rest.api.factory.impl.exception.UnknownPmVendor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

// TODO: 05/11/2023 Here create Json responses with all the information.
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String INTERNAL_SERVER_ERROR = "Internal Server Error";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RestResponseException.class)
    public ResponseEntity<String> handleVendorResponseErrors(RestResponseException ex) {
        var msg = "Error while sending request to PM vendor. Request: X. Vendor: Y.";
        return new ResponseEntity<>(msg, new HttpHeaders(), Objects.requireNonNull(HttpStatus.resolve(ex.getResponseStatusCode())));
    }

    @ExceptionHandler(DeserializationException.class)
    public ResponseEntity<String> handleDeserializationError(DeserializationException ex) {
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RestCallException.class)
    public ResponseEntity<String> handleRestCallError(RestCallException ex) {
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnknownPmVendor.class)
    public ResponseEntity<String> handleUnknownVendorError(UnknownPmVendor ex) {
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

}

