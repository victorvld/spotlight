package os.spotlight.link.rest.api.error.handling.exceptions;

public class DeserializationException extends RuntimeException {
    public DeserializationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
