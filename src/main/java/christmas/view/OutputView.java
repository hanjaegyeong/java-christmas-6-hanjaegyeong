package christmas.view;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public static void printOrderSummary(String text) {
        System.out.println("<주문 메뉴>");
        System.out.println(text);
    }
}
