package rpn.exception;

public class RpnException extends Exception {
    public final String remainingExpression;

    public RpnException(String message, String remainingExpression) {
        super(message);
        this.remainingExpression = remainingExpression;
    }

    public RpnException(String message) {
        super(message);
        this.remainingExpression = null;
    }

}
