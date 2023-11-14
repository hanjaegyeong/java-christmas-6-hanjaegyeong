package christmas.domain.strategy;

import christmas.domain.DiscountEvents;
import christmas.domain.OrderProcessor;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuCategory;

import java.util.EnumMap;

public class WeekdayDiscountStrategy implements BasicDiscountStrategy {
    private final OrderProcessor orderProcessor;
    private final DiscountEvents discountPrices;

    public WeekdayDiscountStrategy(OrderProcessor orderProcessor, DiscountEvents discountPrices) {
        this.orderProcessor = orderProcessor;
        this.discountPrices = discountPrices;
    }

    @Override
    public void applyDiscount() {
        EnumMap<Menu, Integer> orderMenus = orderProcessor.getOrderMenus();

        int weekdayDiscount  = calculateDiscountedPrice(orderMenus);

        if (weekdayDiscount != 0) {
            discountPrices.addDiscountEvent("평일 할인", weekdayDiscount);
        }
    }

    private static int calculateDiscountedPrice(EnumMap<Menu, Integer> orderMenus) {
        int weekdayDiscount = 0;
        for (Menu menu : Menu.values()) { // menu.getCategory()함수호출 매번 하지 않고 변수 만들기
            if (menu.getCategory() == MenuCategory.DESSERT) {
                int quantity = orderMenus.getOrDefault(menu, 0);
                weekdayDiscount -= quantity * 2023;
            }
        }
        return weekdayDiscount;
    }
}
