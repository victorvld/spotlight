package os.spotlight.link.rest.api.gateway.exception;

public class DeserializationException extends RuntimeException {
    public DeserializationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
