package christmas.domain.events.discounts.decorators;

import christmas.domain.events.DiscountEvents;
import christmas.domain.events.discounts.strategies.BasicDiscountStrategy;

import java.util.Set;

public class AdditionalDiscountDecoratorFactory {
    private static final Set<Integer> SPECIAL_DAYS = Set.of(3, 10, 17, 24, 25, 31);
    private static final int CHRISTMAS_DAY = 25;

    public static BasicDiscountStrategy createDiscountStrategy(int day, BasicDiscountStrategy discountStrategy, DiscountEvents discountEvents) {
        if (isSpecialDay(day)) {
            discountStrategy = new SpecialDiscountDecorator(discountStrategy, discountEvents);
        }

        if (isChristmasDDay(day)) {
            discountStrategy = new ChristmasDDayDiscountDecorator(discountStrategy, day, discountEvents);
        }

        return discountStrategy;
    }

    private static boolean isSpecialDay(int day) {
        return SPECIAL_DAYS.contains(day);
    }

    private static boolean isChristmasDDay(int day) {
        return day <= CHRISTMAS_DAY;
    }
}
