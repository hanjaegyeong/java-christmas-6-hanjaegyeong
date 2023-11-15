package christmas.domain.events.discounts.strategies;

import christmas.domain.events.DiscountEvents;
import christmas.domain.order.Order;

import static christmas.domain.order.ReservationDayProcessor.isWeekend;

public class BasicDiscountStrategyFactory {
    public static BasicDiscountStrategy createDiscountStrategy(int reservationDay, Order orderProcessor, DiscountEvents discountEvents) {
        if (isWeekend(reservationDay)) {
            return new WeekendDiscountStrategy(orderProcessor, discountEvents);
        }
        return new WeekdayDiscountStrategy(orderProcessor, discountEvents);
    }
}
