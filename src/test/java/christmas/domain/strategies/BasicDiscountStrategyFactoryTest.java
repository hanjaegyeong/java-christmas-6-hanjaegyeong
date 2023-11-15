package christmas.domain.strategies;

import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.events.DiscountEvents;
import christmas.domain.order.Order;
import christmas.domain.events.discounts.strategies.BasicDiscountStrategy;
import christmas.domain.events.discounts.strategies.BasicDiscountStrategyFactory;
import christmas.domain.events.discounts.strategies.WeekdayDiscountStrategy;
import christmas.domain.events.discounts.strategies.WeekendDiscountStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class BasicDiscountStrategyFactoryTest {
    private Order orderProcessor;
    private DiscountEvents discountEvents;

    @BeforeEach
    public void setUp() {
        orderProcessor = mock(Order.class);
        discountEvents = mock(DiscountEvents.class);
    }

    @Test
    @DisplayName("주말에 대한 할인 전략 생성 테스트")
    public void testCreateDiscountStrategy_Weekend() {
        int reservationDay = 2; // 토요일
        BasicDiscountStrategy strategy = BasicDiscountStrategyFactory.createDiscountStrategy(reservationDay, orderProcessor, discountEvents);

        assertTrue(strategy instanceof WeekendDiscountStrategy);
    }

    @Test
    @DisplayName("평일에 대한 할인 전략 생성 테스트")
    public void testCreateDiscountStrategy_Weekday() {
        int reservationDay = 8; // 수요일
        BasicDiscountStrategy strategy = BasicDiscountStrategyFactory.createDiscountStrategy(reservationDay, orderProcessor, discountEvents);

        assertTrue(strategy instanceof WeekdayDiscountStrategy);
    }
}
