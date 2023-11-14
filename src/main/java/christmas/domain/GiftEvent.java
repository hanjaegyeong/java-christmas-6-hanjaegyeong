package christmas.domain;

public class GiftEvent {
    public static String generateGiftEventOutput(int totalPrice, DiscountEvents discountPrices) {
        if (totalPrice >= 120_000) {
            discountPrices.addDiscountEvent("증정 이벤트", 25_000);
            return "샴페인 1개";
        } else {
            return "없음";
        }
    }
}
