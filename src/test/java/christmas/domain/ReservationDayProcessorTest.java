package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationDayProcessorTest {
    @Test
    @DisplayName("유효하지 않은 예약일에 대해 예외를 던져야 한다.")
    public void testValidateDay_InvalidDate() {
        int invalidDay = 32;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ReservationDayProcessor.validateDay(invalidDay);
        });

        assertEquals("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.", exception.getMessage());
    }

    @Test
    @DisplayName("유효한 예약일은 예외를 던지지 않아야 한다.")
    public void testValidateDay_ValidDate() {
        int validDay = 15;

        assertDoesNotThrow(() -> ReservationDayProcessor.validateDay(validDay));
    }

    @Test
    @DisplayName("예약일이 주말인 경우 true를 반환해야 한다.")
    public void testIsWeekend_Weekend() {
        // 2023년 12월 3일은 일요일
        int weekendDay = 3;

        assertTrue(ReservationDayProcessor.isWeekend(weekendDay));
    }

    @Test
    @DisplayName("예약일이 평일인 경우 false를 반환해야 한다.")
    public void testIsWeekend_Weekday() {
        // 2023년 12월 1일은 금요일
        int weekdayDay = 1;

        assertFalse(ReservationDayProcessor.isWeekend(weekdayDay));
    }
}
