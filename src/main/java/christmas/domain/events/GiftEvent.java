package christmas.domain.events;

import static christmas.utils.Constants.NOTHING;

public class GiftEvent {
    private static final int GIFT_EVENT_THRESHOLD = 120_000;
    private static final int GIFT_EVENT_DISCOUNT_AMOUNT = 25_000;
    private static final String GIFT_EVENT_NAME = "증정 이벤트";
    private static final String GIFT_DESCRIPTION = "샴페인 1개";

    public static String generateGiftEventOutput(int totalAppliedEventsAmount, DiscountEvents discountEvents) {
        if (totalAppliedEventsAmount >= GIFT_EVENT_THRESHOLD) {
            discountEvents.addDiscountEvent(GIFT_EVENT_NAME, GIFT_EVENT_DISCOUNT_AMOUNT);
            return GIFT_DESCRIPTION;
        } else {
            return NOTHING;
        }
    }
}