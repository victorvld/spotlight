package os.spotlight.adpater.response.handling.strategy;

/**
 * Response Handling Strategy contract based on a given status code. For instance throw an exception,
 * retry until maxRetryTimes is reach, etc.
 */
public interface ResponseHandlingStrategy {
    String handleResponse(Integer statusCode, String bodyResponse);
}
