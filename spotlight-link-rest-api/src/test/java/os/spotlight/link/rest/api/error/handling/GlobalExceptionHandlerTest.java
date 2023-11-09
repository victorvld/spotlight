package os.spotlight.link.rest.api.error.handling;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import os.spotlight.link.rest.api.dto.GetBoardsRequest;
import os.spotlight.link.rest.api.error.handling.exceptions.DeserializationException;
import os.spotlight.link.rest.api.error.handling.exceptions.RestCallException;
import os.spotlight.link.rest.api.error.handling.exceptions.RestResponseException;
import os.spotlight.link.rest.api.error.handling.exceptions.UnknownPmVendor;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler exceptionHandler;

    @Test
    void handleValidationErrorsTest() {
        var bindingResults = new DataBinder(new GetBoardsRequest("acc1")).getBindingResult();
        var exception = new MethodArgumentNotValidException((MethodParameter) null, bindingResults);

        var response = exceptionHandler.handleValidationErrors(exception);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void handleVendorResponseErrorsTest() {
        var msg = "Error while sending request to PM vendor. Request: X. Vendor: Y.";
        var httpStatus = 400;
        var exception = new RestResponseException(httpStatus, "Error");

        var response = exceptionHandler.handleVendorResponseErrors(exception);

        Assertions.assertEquals(HttpStatus.resolve(httpStatus), response.getStatusCode());
        Assertions.assertEquals(msg, response.getBody());
    }

    @Test
    void handleDeserializationErrorTest() {
        var exception = new DeserializationException("error", new RuntimeException());

        var response = exceptionHandler.handleDeserializationError(exception);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals("Internal Server Error", response.getBody());
    }

    @Test
    void handleRestCallErrorTest() {
        var exception = new RestCallException("error", new RuntimeException());

        var response = exceptionHandler.handleRestCallError(exception);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals("Internal Server Error", response.getBody());
    }

    @Test
    void handleUnknownPmVendorErrorTest() {
        var exception = new UnknownPmVendor("wrongType");

        var response = exceptionHandler.handleUnknownVendorError(exception);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals("Internal Server Error", response.getBody());
    }

}
