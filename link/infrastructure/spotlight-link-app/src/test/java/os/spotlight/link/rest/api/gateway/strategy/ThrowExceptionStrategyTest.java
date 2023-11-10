package os.spotlight.link.rest.api.gateway.strategy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import os.spotlight.link.factory.adpater.response.handling.strategy.ResponseHandlingStrategy;
import os.spotlight.link.rest.api.exception.RestResponseException;

class ThrowExceptionStrategyTest {
    private final ResponseHandlingStrategy underTest = new ThrowExceptionStrategy();
    @Test
    void whenExternalStatusCodeResponseIs200ThenThrowRestResponseException() {
        var responseBody = "body";
        var result = underTest.handleResponse(200, responseBody);
        Assertions.assertEquals(responseBody, result);

    }
    @Test
    void whenExternalStatusCodeResponseIsNotSuccessfulThenThrowRestResponseException() {
        var errorStatusCode = 400;
        var result = Assertions.assertThrows(RestResponseException.class, () -> underTest.handleResponse(errorStatusCode, "response"));

        Assertions.assertEquals(errorStatusCode, result.getResponseStatusCode());
    }
}
