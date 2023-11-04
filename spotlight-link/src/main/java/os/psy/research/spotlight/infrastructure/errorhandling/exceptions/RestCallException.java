package os.psy.research.spotlight.infrastructure.errorhandling.exceptions;

public class RestCallException extends RuntimeException {
    public RestCallException(String message, Throwable cause) {
        super(message, cause);
    }
}
