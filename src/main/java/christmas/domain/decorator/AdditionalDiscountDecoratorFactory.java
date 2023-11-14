package christmas.domain.decorator;

import christmas.domain.DiscountEvents;
import christmas.domain.strategy.BasicDiscountStrategy;

import java.util.ArrayList;
import java.util.List;

public class AdditionalDiscountDecoratorFactory {
    public static BasicDiscountStrategy createDiscountStrategy(int day, BasicDiscountStrategy discountStrategy, DiscountEvents discountEvents) {

        if (isSpecialDay(day)) {
            discountStrategy = new SpecialDiscountDecorator(discountStrategy, discountEvents);
        }

        if (isChristmasDDay(day)) {
            discountStrategy = new ChristmasDDayDiscountDecorator(discountStrategy, day, discountEvents);
        }

        return discountStrategy;
    }

    private static boolean isSpecialDay(int day) {
        // 별표 날짜 판단 로직
        // 여기 리스트 부분 상수화, 클린코드로 변경하기!
        List<Integer> specialDay = new ArrayList<>(List.of(3, 10, 17, 24, 25, 31));
        return specialDay.contains(day);
    }

    private static boolean isChristmasDDay(int day) {
        // 크리스마스 날짜 판단 로직
        return day <= 25;
    }
}