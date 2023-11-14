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
}
