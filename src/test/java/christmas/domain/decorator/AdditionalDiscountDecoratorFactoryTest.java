package christmas.domain.decorator;

import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.DiscountEvents;
import christmas.domain.strategy.BasicDiscountStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class AdditionalDiscountDecoratorFactoryTest {
    @Test
    @DisplayName("별표가 표시된 날인 경우 특별 할인 데코레이터를 생성해야 한다.")
    public void testCreateDiscountStrategy_SpecialDay() {
        int day = 10;
        BasicDiscountStrategy discountStrategy = mock(BasicDiscountStrategy.class);
        DiscountEvents discountEvents = mock(DiscountEvents.class);

        BasicDiscountStrategy strategy = AdditionalDiscountDecoratorFactory.createDiscountStrategy(day, discountStrategy, discountEvents);

        strategy.applyDiscount();

        verify(discountEvents, times(1)).addDiscountEvent(eq("특별 할인"), anyInt());
    }

    @Test
    @DisplayName("크리스마스 디데이에 속할 경우 크리스마스 디데이 할인 데코레이터를 생성해야 한다.")
    public void testCreateDiscountStrategy_ChristmasDDay() {
        int day = 24;
        BasicDiscountStrategy discountStrategy = mock(BasicDiscountStrategy.class);
        DiscountEvents discountEvents = mock(DiscountEvents.class);

        BasicDiscountStrategy strategy = AdditionalDiscountDecoratorFactory.createDiscountStrategy(day, discountStrategy, discountEvents);

        strategy.applyDiscount();

        verify(discountEvents, times(1)).addDiscountEvent(eq("크리스마스 디데이 할인"), anyInt());
    }

    @Test
    @DisplayName("특별한 날도 아니고 크리스마스 디데이도 아닌 경우 기본 할인 전략을 반환해야 한다.")
    public void testCreateDiscountStrategy_NoSpecialDay() {
        int day = 28;
        BasicDiscountStrategy discountStrategy = mock(BasicDiscountStrategy.class);
        DiscountEvents discountEvents = mock(DiscountEvents.class);

        BasicDiscountStrategy strategy = AdditionalDiscountDecoratorFactory.createDiscountStrategy(day, discountStrategy, discountEvents);

        assertSame(discountStrategy, strategy);
    }
}
