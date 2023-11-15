package christmas.domain;

import static org.junit.jupiter.api.Assertions.*;

import christmas.exceptions.InvalidOrderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InvalidOrderExceptionTest {

    @Test
    @DisplayName("InvalidOrderException 생성 테스트")
    public void testCreateInvalidOrderException() {
        String errorMessage = "유효하지 않은 주문입니다.";
        InvalidOrderException exception = new InvalidOrderException(errorMessage);

        assertEquals("[ERROR] " + errorMessage + System.lineSeparator(), exception.getMessage());
    }
}
