package christmas.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// 혜택 내역 + 총 할인 금액
public class DiscountEvents {
    private final Map<String, Integer> discountEvents = new HashMap<>();

    public void addDiscountEvent(String eventName, int discountAmount) {
        // 할인 이벤트를 Map에 추가
        discountEvents.put(eventName, discountAmount);
    }
}
