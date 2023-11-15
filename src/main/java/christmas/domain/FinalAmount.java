package christmas.domain;

import static christmas.utils.Constants.WON;

public class FinalAmount {
    public static String calculateAndFormatFinalAmountOutput(int totalOrderAmount, DiscountEvents discountEvents) {
        int finalAmount = calculateFinalAmountOutput(totalOrderAmount, discountEvents);
        return formatFinalAmountOutput(finalAmount);
    }

    private static int calculateFinalAmountOutput(int totalOrderAmount, DiscountEvents discountEvents) {
        return totalOrderAmount - discountEvents.getTotalDiscountAmount();
    }

    private static String formatFinalAmountOutput(int finalAmount) {
        return String.format("-%,d%s", finalAmount, WON);
    }
}
