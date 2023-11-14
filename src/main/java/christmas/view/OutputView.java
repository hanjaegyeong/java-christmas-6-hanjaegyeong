package christmas.view;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String CURRENCY_SUFFIX = "원";
    private static final String ORDER_SUMMARY_TITLE = "<주문 메뉴>";
    private static final String TOTAL_ORDER_AMOUNT_TITLE = "<할인 전 총주문 금액>";
    private static final String GIFT_EVENT_SUMMARY_TITLE = "<증정 메뉴>";
    private static final String DISCOUNT_SUMMARY_TITLE = "<혜택 내역>";
    private static final String TOTAL_BENEFITS_TITLE = "<총혜택 금액>";
    private static final String FINAL_AMOUNT_TITLE = "<할인 후 예상 결제 금액>";
    private static final String EVENT_BADGE_TITLE = "<12월 이벤트 배지>";

    public static void printOrderSummary(String text) {
        printWithTitle(ORDER_SUMMARY_TITLE, text);
    }

    public static void printTotalOrderAmount(String text) {
        printWithTitle(TOTAL_ORDER_AMOUNT_TITLE, text);
    }

    public static void printGiftEventSummary(String text) {
        printWithTitle(GIFT_EVENT_SUMMARY_TITLE, text);
    }

    public static void printDiscountSummary(String text) {
        printWithTitle(DISCOUNT_SUMMARY_TITLE, text);
    }

    public static void printTotalBenefits(String text) {
        printWithTitle(TOTAL_BENEFITS_TITLE, text);
    }

    public static void printFinalAmount(String text) {
        printWithTitle(FINAL_AMOUNT_TITLE, text);
    }

    public static void printEventBadge(String text) {
        printWithTitle(EVENT_BADGE_TITLE, text);
    }

    public static void printExceptionMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    private static void printWithTitle(String title, String text) {
        System.out.println(title);
        System.out.println(text);
        System.out.print(LINE_SEPARATOR);
    }
}
