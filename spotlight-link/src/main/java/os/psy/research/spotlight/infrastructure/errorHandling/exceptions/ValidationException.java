package os.psy.research.spotlight.infrastructure.errorHandling.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String msg) {
        super(msg);
    }
}
