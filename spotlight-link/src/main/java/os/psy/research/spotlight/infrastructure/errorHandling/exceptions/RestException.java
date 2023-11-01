package os.psy.research.spotlight.infrastructure.errorHandling.exceptions;

public class RestException extends RuntimeException {
    public RestException(String message, Throwable cause) {
        super(message, cause);
    }
}