package christmas.domain.decorator;

import christmas.domain.DiscountEvents;
import christmas.domain.strategy.BasicDiscountStrategy;

public class ChristmasDDayDiscountDecorator extends AdditionalDiscountDecorator {
    private static final int BASE_DISCOUNT_AMOUNT = 1000;
    private static final int DAILY_INCREMENT = 100;

    private final int reservationDay;
    private final DiscountEvents discountEvents;

    public ChristmasDDayDiscountDecorator(BasicDiscountStrategy strategy, int reservationDay, DiscountEvents discountEvents) {
        super(strategy);
        this.reservationDay = reservationDay;
        this.discountEvents = discountEvents;
    }

    @Override
    public void applyDiscount() {
        super.applyDiscount();

        int christmasDDayDiscount = calculateChristmasDDayDiscount();

        if (christmasDDayDiscount != 0) {
            discountEvents.addDiscountEvent("크리스마스 디데이 할인", christmasDDayDiscount);
        }
    }

    private int calculateChristmasDDayDiscount() {
        return (reservationDay - 1) * DAILY_INCREMENT + BASE_DISCOUNT_AMOUNT;
    }
}
