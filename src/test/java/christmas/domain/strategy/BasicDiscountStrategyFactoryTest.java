package christmas.domain.strategy;

import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.DiscountEvents;
import christmas.domain.Order;
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
