package os.psy.research.spotlight.infrastructure.errorhandling.exceptions;

public class DeserializationException extends RuntimeException {
    public DeserializationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
