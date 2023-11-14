package christmas.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReservationDayProcessorTest {

    @Test
    @DisplayName("유효한 날짜인 경우 예외가 발생하지 않아야 한다.")
    public void testValidateDay_ValidDay() {
        assertDoesNotThrow(() -> ReservationDayProcessor.validateDay(15));
    }

    @Test
    @DisplayName("유효하지 않은 날짜인 경우 예외가 발생해야 한다.")
    public void testValidateDay_InvalidDay() {
        assertThrows(IllegalArgumentException.class, () -> ReservationDayProcessor.validateDay(0));
        assertThrows(IllegalArgumentException.class, () -> ReservationDayProcessor.validateDay(32));
    }

    @Test
    @DisplayName("주말인 경우 true를 반환해야 한다.")
    public void testIsWeekend_WeekendDay() {
        assertTrue(ReservationDayProcessor.isWeekend(3)); // 토요일
        assertTrue(ReservationDayProcessor.isWeekend(4)); // 일요일
    }

    @Test
    @DisplayName("평일인 경우 false를 반환해야 한다.")
    public void testIsWeekend_Weekday() {
        assertFalse(ReservationDayProcessor.isWeekend(6)); // 금요일
        assertFalse(ReservationDayProcessor.isWeekend(10)); // 수요일
    }
}
