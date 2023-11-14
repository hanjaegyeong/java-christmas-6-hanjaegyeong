package christmas.domain;

public class ReservationDayProcesser {
    public static void validateDay(int reservationDay) throws IllegalArgumentException {
        if (reservationDay < 1 || reservationDay > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
