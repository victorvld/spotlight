package os.spotlight.link.rest.api.error.handling.exceptions;

public class RestCallException extends RuntimeException {
    public RestCallException(String message, Throwable cause) {
        super(message, cause);
    }
}
