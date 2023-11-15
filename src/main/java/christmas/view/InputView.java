package christmas.view;

import christmas.exceptions.InvalidDayException;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static christmas.utils.Constants.LINE_SEPARATOR;

public class InputView {
    private static final String INVALID_DATE_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public static int inputReservationDay() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n" +
                "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        int day = readInt();
        System.out.print(LINE_SEPARATOR);
        return day;
    }

    private static int readInt() {
        try {
            return Integer.parseInt(readLine());
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidDayException(INVALID_DATE_MESSAGE);
        }
    }

    public static String inputOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String order  = readLine();
        System.out.print(LINE_SEPARATOR);
        return order ;
    }
}
