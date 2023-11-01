package os.psy.research.spotlight.infrastructure.errorHandling.exceptions;

public class DeserializationException extends RuntimeException {
    public DeserializationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
