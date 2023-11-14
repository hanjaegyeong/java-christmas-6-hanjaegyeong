package christmas.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// 혜택 내역 + 총 할인 금액
public class DiscountEvents {
    private final Map<String, Integer> discountEvents = new HashMap<>();
    private int totalDiscountPrice = 0; // 총 할인 금액 래핑 고민하기***

    public int getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    public void addDiscountEvent(String eventName, int discountAmount) {
        // 할인 이벤트를 Map에 추가
        discountEvents.put(eventName, discountAmount);
        if (!Objects.equals(eventName, "증정 이벤트")) {
            totalDiscountPrice += discountAmount;
        }
    }

    public String generateAllDiscountEventsString() {
        StringBuilder formattedDiscountEvents = new StringBuilder();

        if (discountEvents.isEmpty()) {
            return "없음";
        }

        int count = 0;
        for (Map.Entry<String, Integer> discountEntry : discountEvents.entrySet()) {
            String eventName = discountEntry.getKey();
            int discountPrice = discountEntry.getValue();

            String formattedDiscount = String.format("%s: -%,d원", eventName, discountPrice);
            formattedDiscountEvents.append(formattedDiscount);

            // 현재 엔트리가 마지막이 아니라면 줄바꿈 추가
            if (++count < discountEvents.size()) {
                formattedDiscountEvents.append("\n");
            }
        }

        return formattedDiscountEvents.toString();
    }

    public int calculateTotalDiscountPrice() {
        return discountEvents.values().stream().mapToInt(Integer::intValue).sum();
    }
}
