package christmas.domain.exceptions;

import static christmas.utils.Constants.LINE_SEPARATOR;

public class InvalidOrderException extends IllegalArgumentException {
    public InvalidOrderException(String message) {
        super("[ERROR] " + message + LINE_SEPARATOR);
    }
}