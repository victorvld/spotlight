package os.spotlight.link.rest.api.gateway.strategy;

import os.spotlight.link.factory.adpater.response.handling.strategy.ResponseHandlingStrategy;
import os.spotlight.link.rest.api.gateway.exception.RestResponseException;

public class ThrowExceptionStrategy implements ResponseHandlingStrategy {
    @Override
    public String handleResponse(Integer statusCode, String bodyResponse) {
        if (statusCode == 200) {
            return bodyResponse;
        }
        throw new RestResponseException(statusCode, "Rest Client Error Response.");
    }
}
