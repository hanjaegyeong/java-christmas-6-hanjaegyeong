package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReservationDayProcesser {
    public static void validateDay(int reservationDay) throws IllegalArgumentException {
        if (reservationDay < 1 || reservationDay > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public static boolean isWeekend(int reservationDay) {
        LocalDate reservationDate = parseDate(reservationDay);
        DayOfWeek dayOfWeek = reservationDate.getDayOfWeek(); // 특정 날짜의 요일 가져오는 매서드

        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    private static LocalDate parseDate(int reservationDay) {
        String date = String.format("202312%02d", reservationDay); // %02d는 입력받은 day를 두 자리 수로 만들어주는 포매터
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(date, formatter);
    }
}
