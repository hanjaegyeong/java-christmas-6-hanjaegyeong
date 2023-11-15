package christmas.domain.decorator;

import christmas.domain.DiscountEvents;
import christmas.domain.strategy.BasicDiscountStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class SpecialDiscountDecoratorTest {
    @Test
    @DisplayName("특별 할인이 적용되어야 한다.")
    public void testApplyDiscount() {
        BasicDiscountStrategy mockStrategy = mock(BasicDiscountStrategy.class);
        DiscountEvents discountEvents = new DiscountEvents();
        SpecialDiscountDecorator decorator = new SpecialDiscountDecorator(mockStrategy, discountEvents);

        decorator.applyDiscount();

        assertEquals(1000, discountEvents.getTotalDiscountAmount());
    }
}
