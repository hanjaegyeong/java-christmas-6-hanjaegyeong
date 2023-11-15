package christmas.exceptions;

import static christmas.utils.Constants.LINE_SEPARATOR;

public class InvalidDayException extends IllegalArgumentException {
    public InvalidDayException(String message) {
        super("[ERROR] " + message + LINE_SEPARATOR);
    }
}