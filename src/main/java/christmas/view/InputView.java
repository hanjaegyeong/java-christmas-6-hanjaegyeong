package christmas.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class InputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String INVALID_DATE_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public static int inputReservationDay() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n" +
                "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        int input = readInt();
        System.out.print(LINE_SEPARATOR);
        return input;
    }

    private static int readInt() {
        String inputInt = readLine();
        return stringToInt(inputInt);
    }

    public static int stringToInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_DATE_MESSAGE);
        }
    }

    public static String inputOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = readLine();
        System.out.print(LINE_SEPARATOR);
        return input;
    }
}
