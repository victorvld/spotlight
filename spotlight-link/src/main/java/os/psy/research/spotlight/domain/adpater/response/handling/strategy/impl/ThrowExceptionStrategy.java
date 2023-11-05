package os.psy.research.spotlight.domain.adpater.response.handling.strategy.impl;

import os.psy.research.spotlight.domain.adpater.response.handling.strategy.ResponseHandlingStrategy;
import os.psy.research.spotlight.infrastructure.errorhandling.exceptions.RestResponseException;

public class ThrowExceptionStrategy implements ResponseHandlingStrategy {
    @Override
    public String handleResponse(Integer statusCode, String bodyResponse) {
        if (statusCode == 200) {
            return bodyResponse;
        }
        throw new RestResponseException(statusCode, "Rest Client Error Response.");
    }
}
