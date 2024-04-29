package hud.emailverification.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String tokenInvalidOrNotFound) {
        super(tokenInvalidOrNotFound);
    }
}
