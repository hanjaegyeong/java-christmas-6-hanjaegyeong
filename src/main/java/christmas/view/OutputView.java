package christmas.view;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public static void printOrderSummary(String text) {
        System.out.println("<주문 메뉴>");
        System.out.println(text);
    }

    public static void printTotalOrderPrice(String text) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(text);
        System.out.print(LINE_SEPARATOR);
    }

    public static void printGiftEventSummary(String text) {
        System.out.println("<증정 메뉴>");
        System.out.println(text);
        System.out.print(LINE_SEPARATOR);
    }

    public static void printDiscountSummary(String text) {
        System.out.println("<혜택 내역>");
        System.out.println(text);
        System.out.print(LINE_SEPARATOR);
    }

    public static void printTotalBenefits(String text) {
        System.out.println("<총혜택 금액>");
        System.out.println(text);
        System.out.print(LINE_SEPARATOR);
    }

    public static void printFinalPrice(int price) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%,d원\n", price); //여기 -원도 스트링 만들어서 오기
        System.out.print(LINE_SEPARATOR);
    }
}
