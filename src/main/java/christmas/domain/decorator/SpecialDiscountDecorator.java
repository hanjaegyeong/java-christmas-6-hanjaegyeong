package christmas.domain.decorator;

import christmas.domain.DiscountEvents;
import christmas.domain.strategy.BasicDiscountStrategy;

public class SpecialDiscountDecorator extends AdditionalDiscountDecorator {
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1000;
    private static final String SPECIAL_DISCOUNT = "특별 할인";

    private final DiscountEvents discountEvents;

    public SpecialDiscountDecorator(BasicDiscountStrategy strategy, DiscountEvents discountEvents) {
        super(strategy);
        this.discountEvents = discountEvents;
    }

    @Override
    public void applyDiscount() {
        super.applyDiscount();

        int specialDiscount = calculateSpecialDiscount();

        if (specialDiscount > 0) {
            discountEvents.addDiscountEvent(SPECIAL_DISCOUNT, specialDiscount);
        }
    }

    private int calculateSpecialDiscount() {
        return SPECIAL_DISCOUNT_AMOUNT;
    }
}