package os.spotlight.link.rest.api.exception;

public class DeserializationException extends RuntimeException {
    public DeserializationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
