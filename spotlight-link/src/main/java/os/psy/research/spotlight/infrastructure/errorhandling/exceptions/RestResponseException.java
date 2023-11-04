package os.psy.research.spotlight.infrastructure.errorhandling.exceptions;

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
