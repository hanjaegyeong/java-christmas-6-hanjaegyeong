package christmas.domain.events;

import java.util.Arrays;
import java.util.Comparator;

import static christmas.utils.Constants.NOTHING;

public enum EventBadge {
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String badgeName;
    private final int thresholdAmount;

    EventBadge(String badgeName, int discountAmount) {
        this.badgeName = badgeName;
        this.thresholdAmount = discountAmount;
    }

    public static String findBadgeByTotalAppliedEventsAmount(int totalAppliedEventsAmount) {
        EventBadge eventBadge = Arrays.stream(values())
                .filter(badge -> badge.thresholdAmount <= totalAppliedEventsAmount)
                .max(Comparator.comparingInt(badge -> badge.thresholdAmount))
                .orElse(null);

        if (eventBadge != null) {
            return eventBadge.badgeName;
        }
        return NOTHING;
    }
}
