package christmas.domain.strategy;

import christmas.domain.DiscountEvents;
import christmas.domain.OrderProcessor;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuCategory;

import java.util.EnumMap;

public class WeekendDiscountStrategy implements BasicDiscountStrategy {
    private final OrderProcessor orderProcessor;
    private final DiscountEvents discountEvents;

    private static final String WEEKEND_DISCOUNT = "주말 할인";
    private static final int MAIN_COURSE_DISCOUNT_AMOUNT = 2023;

    public WeekendDiscountStrategy(OrderProcessor orderProcessor, DiscountEvents discountEvents) {
        this.orderProcessor = orderProcessor;
        this.discountEvents = discountEvents;
    }

    @Override
    public void applyDiscount() {
        EnumMap<Menu, Integer> orderMenus = orderProcessor.getOrderMenus();

        int weekendDiscount = calculateDiscountedPrice(orderMenus);

        if (weekendDiscount != 0) {
            discountEvents.addDiscountEvent(WEEKEND_DISCOUNT, weekendDiscount);
        }
    }

    private static int calculateDiscountedPrice(EnumMap<Menu, Integer> orderMenus) {
        int discountedPrice = 0;
        for (Menu menu : orderMenus.keySet()) {
            if (menu.getCategory() == MenuCategory.MAIN_COURSE) {
                int quantity = orderMenus.getOrDefault(menu, 0);
                discountedPrice += quantity * MAIN_COURSE_DISCOUNT_AMOUNT;
            }
        }
        return discountedPrice;
    }
}
