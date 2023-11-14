package christmas.domain.decorator;

import christmas.domain.DiscountEvents;
import christmas.domain.strategy.BasicDiscountStrategy;

public class SpecialDiscountDecorator extends AdditionalDiscountDecorator {
    private final DiscountEvents discountEvents;
    public SpecialDiscountDecorator(BasicDiscountStrategy strategy, DiscountEvents discountEvents) {
        super(strategy);
        this.discountEvents = discountEvents;
    }

    @Override
    public void applyDiscount() {
        super.applyDiscount();
        int specialDiscount = calculateDiscountPrice();

        if (specialDiscount != 0) {
            discountEvents.addDiscountEvent("특별 할인", specialDiscount);
        }
    }

    private int calculateDiscountPrice() {
        return 1000; //상수화!
    }
}