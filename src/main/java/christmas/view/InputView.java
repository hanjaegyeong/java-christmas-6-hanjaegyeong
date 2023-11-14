package christmas.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class InputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();

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

    public static int stringToInt(String text) throws IllegalArgumentException {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
