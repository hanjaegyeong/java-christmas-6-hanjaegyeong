package christmas.domain.strategy;

import christmas.domain.DiscountEvents;
import christmas.domain.OrderProcessor;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuCategory;

import java.util.EnumMap;

public class WeekendDiscountStrategy implements BasicDiscountStrategy {
    private final OrderProcessor orderProcessor;
    private final DiscountEvents discountEvents;

    public WeekendDiscountStrategy(OrderProcessor orderProcessor, DiscountEvents discountEvents) {
        this.orderProcessor = orderProcessor;
        this.discountEvents = discountEvents;
    }

    @Override
    public void applyDiscount() {
        EnumMap<Menu, Integer> orderMenus = orderProcessor.getOrderMenus();

        int weekendDiscount = calculateDiscountedPrice(orderMenus);

        if (weekendDiscount != 0) {
            discountEvents.addDiscountEvent("주말 할인", weekendDiscount);
        }
    }

    // 여기 코드 좀 더 뜯어보기!
    private static int calculateDiscountedPrice(EnumMap<Menu, Integer> orderMenus) {
        int discountedPrice = 0;
        for (Menu menu : Menu.values()) {
            if (menu.getCategory() == MenuCategory.MAIN_COURSE) {
                int quantity = orderMenus.getOrDefault(menu, 0);
                discountedPrice += quantity * 2023; // 상수화!
            }
        }
        return discountedPrice;
    }
}
