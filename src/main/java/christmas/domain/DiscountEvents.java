package christmas.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static christmas.utils.constants.*;

public class DiscountEvents {
    private static final String GIFT_EVENT = "증정 이벤트";
    private final Map<String, Integer> appliedEvents = new HashMap<>();
    private int totalDiscountAmount = 0;

    public void addDiscountEvent(String eventName, int discountAmount) {
        appliedEvents.put(eventName, discountAmount);
        updateTotalDiscountAmount(eventName, discountAmount);
    }

    private void updateTotalDiscountAmount(String eventName, int discountAmount) {
        if (!isGiftEvent(eventName)) {
            totalDiscountAmount += discountAmount;
        }
    }

    private boolean isGiftEvent(String eventName) {
        return Objects.equals(eventName, GIFT_EVENT);
    }

    public int getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public String formatAppliedEventsOutput() {
        if (appliedEvents.isEmpty()) {
            return NOTHING;
        }

        return appliedEvents.entrySet().stream()
                .map(entry -> formatDiscountEventOutput(entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    private String formatDiscountEventOutput(String eventName, int discountAmount) {
        return String.format("%s: -%,d%s", eventName, discountAmount, WON);
    }

    public String formatTotalDiscountAmountOutput() {
        if (totalDiscountAmount != 0) {
            return String.format("-%,d%s", totalDiscountAmount, WON);
        }
        return NOTHING;
    }

    public int getTotalAppliedEventsAmount() {
        return appliedEvents.values().stream().mapToInt(Integer::intValue).sum();
    }
}
