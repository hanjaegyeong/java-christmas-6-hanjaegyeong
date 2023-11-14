package christmas.domain.strategy;

import christmas.domain.DiscountEvents;
import christmas.domain.OrderProcessor;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuCategory;

import java.util.EnumMap;

public class WeekdayDiscountStrategy implements BasicDiscountStrategy {
    private final OrderProcessor orderProcessor;
    private final DiscountEvents discountPrices;

    private static final String WEEKDAY_DISCOUNT = "평일 할인";
    private static final int DESSERT_DISCOUNT_AMOUNT = 2023;

    public WeekdayDiscountStrategy(OrderProcessor orderProcessor, DiscountEvents discountPrices) {
        this.orderProcessor = orderProcessor;
        this.discountPrices = discountPrices;
    }

    @Override
    public void applyDiscount() {
        EnumMap<Menu, Integer> orderMenus = orderProcessor.getOrderMenus();

        int weekdayDiscount = calculateDiscountedPrice(orderMenus);

        if (weekdayDiscount != 0) {
            discountPrices.addDiscountEvent(WEEKDAY_DISCOUNT, weekdayDiscount);
        }
    }

    private static int calculateDiscountedPrice(EnumMap<Menu, Integer> orderMenus) {
        int weekdayDiscount = 0;

        for (Menu menu : orderMenus.keySet()) {
            if (menu.getCategory() == MenuCategory.DESSERT) {
                int quantity = orderMenus.getOrDefault(menu, 0);
                weekdayDiscount -= quantity * DESSERT_DISCOUNT_AMOUNT;
            }
        }

        return weekdayDiscount;
    }
}
