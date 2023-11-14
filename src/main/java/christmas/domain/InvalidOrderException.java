package christmas.domain;

public class InvalidOrderException extends IllegalArgumentException {
    public InvalidOrderException(String message) {
        super("[ERROR] " + message);
    }
}