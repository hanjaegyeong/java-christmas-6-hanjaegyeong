package christmas.domain.strategy;

import christmas.domain.DiscountEvents;
import christmas.domain.OrderProcessor;

import static christmas.domain.ReservationDayProcesser.isWeekend;

public class BasicDiscountStrategyFactory {
    public static BasicDiscountStrategy createDiscountStrategy(int day, OrderProcessor orderProcessor, DiscountEvents discountEvents) {
        if (isWeekend(day)) {
            return new WeekendDiscountStrategy(orderProcessor, discountEvents);
        }
        return new WeekdayDiscountStrategy(orderProcessor, discountEvents);
    }
}
