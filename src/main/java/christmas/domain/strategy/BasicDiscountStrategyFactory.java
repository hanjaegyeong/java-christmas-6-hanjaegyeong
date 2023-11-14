package christmas.domain.strategy;

import christmas.domain.DiscountEvents;
import christmas.domain.OrderProcessor;

import static christmas.domain.ReservationDayProcessor.isWeekend;

public class BasicDiscountStrategyFactory {
    public static BasicDiscountStrategy createDiscountStrategy(int reservationDay, OrderProcessor orderProcessor, DiscountEvents discountEvents) {
        if (isWeekend(reservationDay)) {
            return new WeekendDiscountStrategy(orderProcessor, discountEvents);
        }
        return new WeekdayDiscountStrategy(orderProcessor, discountEvents);
    }
}
