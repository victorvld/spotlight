package os.spotlight.adapter.tools.impl.strategy;

import os.spotlight.adapter.tools.impl.exception.RestResponseException;
import os.spotlight.link.adapter.tools.response.handling.strategy.ResponseHandlingStrategy;

public class ThrowExceptionStrategy implements ResponseHandlingStrategy {
    @Override
    public String handleResponse(Integer statusCode, String bodyResponse) {
        if (statusCode == 200) {
            return bodyResponse;
        }
        throw new RestResponseException(statusCode, "Rest Client Error Response.");
    }
}
