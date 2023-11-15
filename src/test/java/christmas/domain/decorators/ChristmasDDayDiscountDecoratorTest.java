package christmas.domain.decorators;

import christmas.domain.events.DiscountEvents;
import christmas.domain.events.discounts.decorators.ChristmasDDayDiscountDecorator;
import christmas.domain.events.discounts.strategies.BasicDiscountStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ChristmasDDayDiscountDecoratorTest {
    @Test
    @DisplayName("크리스마스 디데이 할인이 정상적으로 적용되어야 한다.")
    public void testApplyDiscount() {
        BasicDiscountStrategy mockStrategy = mock(BasicDiscountStrategy.class);
        DiscountEvents discountEvents = new DiscountEvents();
        int reservationDay = 23;
        ChristmasDDayDiscountDecorator decorator = new ChristmasDDayDiscountDecorator(mockStrategy, reservationDay, discountEvents);

        decorator.applyDiscount();

        int expectedDiscount = 3200;
        assertEquals(expectedDiscount, discountEvents.getTotalDiscountAmount());
    }
}
