package os.psy.research.spotlight.infrastructure.errorhandling.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String msg) {
        super(msg);
    }
}
