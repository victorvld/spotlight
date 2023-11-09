package os.spotlight.adpater.response.handling.strategy.impl;

import os.spotlight.adpater.response.handling.strategy.ResponseHandlingStrategy;

public class ThrowExceptionStrategy implements ResponseHandlingStrategy {
    @Override
    public String handleResponse(Integer statusCode, String bodyResponse) {
        if (statusCode == 200) {
            return bodyResponse;
        }
        throw new RuntimeException();

//        throw new RestResponseException(statusCode, "Rest Client Error Response.");
    }
}
