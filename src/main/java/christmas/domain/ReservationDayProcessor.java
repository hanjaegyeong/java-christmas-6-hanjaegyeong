package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReservationDayProcessor {
    private static final String INVALID_DATE_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public static void validateDay(int reservationDay) {
        if (reservationDay < 1 || reservationDay > 31) {
            throw new IllegalArgumentException(INVALID_DATE_MESSAGE);
        }
    }

    public static boolean isWeekend(int reservationDay) {
        LocalDate reservationDate = parseDate(reservationDay);
        DayOfWeek dayOfWeek = reservationDate.getDayOfWeek();

        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    private static LocalDate parseDate(int reservationDay) {
        String date = String.format("202312%02d", reservationDay);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(date, formatter);
    }
}
