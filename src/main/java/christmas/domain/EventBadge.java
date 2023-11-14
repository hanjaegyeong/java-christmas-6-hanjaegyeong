package christmas.domain;

import java.util.Arrays;
import java.util.Comparator;

public enum EventBadge {
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String badgeName;
    private final int discountPrice;

    EventBadge(String badgeName, int discountPrice) {
        this.badgeName = badgeName;
        this.discountPrice = discountPrice;
    }

    // 가격에 해당하는 이벤트 배지 찾기
    // 혜택은 benefit?
    public static String findBadgeByPrice(int totalDiscountPrice) {
        EventBadge eventBadge = Arrays.stream(values())
                .filter(badge -> badge.discountPrice <= totalDiscountPrice)
                .max(Comparator.comparingInt(badge -> badge.discountPrice))
                .orElse(null);

        if (eventBadge != null) {
            return eventBadge.badgeName;
        }
        return "없음";
    }
}
