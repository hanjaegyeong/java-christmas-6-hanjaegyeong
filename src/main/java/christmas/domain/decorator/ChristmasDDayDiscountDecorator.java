package christmas.domain.decorator;

import christmas.domain.DiscountEvents;
import christmas.domain.strategy.BasicDiscountStrategy;

// reservationDay, discountPrices를 매개변수로 받아서 필드 세팅하는 것보다 좋은 방법이 없을까?
public class ChristmasDDayDiscountDecorator extends AdditionalDiscountDecorator {
    private final int reservationDay;
    private final DiscountEvents discountEvents;

    public ChristmasDDayDiscountDecorator(BasicDiscountStrategy strategy, int reservationDay, DiscountEvents discountEvents) {
        super(strategy);
        this.reservationDay = reservationDay;
        this.discountEvents = discountEvents;
    }

    @Override
    public void applyDiscount() {
        super.applyDiscount(); // 상위 함수들 연쇄호출
        
        int ChristmasDDayDiscount = calculateDiscountPrice();

        if (ChristmasDDayDiscount != 0) {
            discountEvents.addDiscountEvent("크리스마스 디데이 할인", ChristmasDDayDiscount);
        }
    }

    // 다 상수화!
    private int calculateDiscountPrice() {
        // 할인 금액 계산 (1,000원으로 시작하여 날짜에 따라 100원씩 증가)
        int baseDiscount = 1000;
        return (reservationDay - 1) * 100 + baseDiscount;
    }
}
