package os.psy.research.spotlight.infrastructure.errorhandling;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import os.psy.research.spotlight.presentation.dto.GetBoardsRequest;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler exceptionHandler;
    @Test
    void handleValidationErrors() {
        var bindingResults = new DataBinder(new GetBoardsRequest("acc1")).getBindingResult();

        var exception = new MethodArgumentNotValidException((MethodParameter) null, bindingResults);

        var response = exceptionHandler.handleValidationErrors(exception);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
