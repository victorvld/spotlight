package os.spotlight.adapter.tools.impl.exception;

public class RestResponseException extends RuntimeException {

    final Integer responseStatusCode;
    public RestResponseException(Integer responseStatusCode, String message) {
        super(message);
        this.responseStatusCode = responseStatusCode;
    }

    public Integer getResponseStatusCode() {
        return responseStatusCode;
    }
}
