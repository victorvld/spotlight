package os.spotlight.infrastructure.aspect.errorHandling;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import os.spotlight.presentation.dto.request.GetFocusUnitsRequest;
import os.spotlight.errorHandling.GlobalExceptionHandler;


@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler exceptionHandler;
    @Test
    void handleValidationErrors() {
        var bindingResults = new DataBinder(new GetFocusUnitsRequest("userId")).getBindingResult();

        var exception = new MethodArgumentNotValidException((MethodParameter) null, bindingResults);

        var response = exceptionHandler.handleValidationErrors(exception);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
