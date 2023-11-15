package christmas.domain.strategy;

import christmas.domain.DiscountEvents;
import christmas.domain.Order;
import christmas.domain.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static org.mockito.Mockito.*;

public class WeekendDiscountStrategyTest {
    private Order orderProcessor;
    private DiscountEvents discountEvents;

    @BeforeEach
    public void setUp() {
        orderProcessor = mock(Order.class);
        discountEvents = mock(DiscountEvents.class);
    }

    @Test
    @DisplayName("주말 주문 시 메인메뉴 한 개당 할인이 적용된다.")
    public void testApplyDiscount_WeekendMainCourseDiscount() {
        EnumMap<Menu, Integer> orderMenus = new EnumMap<>(Menu.class);
        orderMenus.put(Menu.SEAFOOD_PASTA, 2);

        when(orderProcessor.getOrderMenus()).thenReturn(orderMenus);

        WeekendDiscountStrategy strategy = new WeekendDiscountStrategy(orderProcessor, discountEvents);
        strategy.applyDiscount();

        verify(discountEvents).addDiscountEvent("주말 할인", 4046);
    }

    @Test
    @DisplayName("주말 주문 중 메인메뉴가 아닌 경우 할인이 적용되지 않는다.")
    public void testApplyDiscount_NoWeekendMainCourseDiscount() {
        EnumMap<Menu, Integer> orderMenus = new EnumMap<>(Menu.class);
        orderMenus.put(Menu.ICE_CREAM, 3);

        when(orderProcessor.getOrderMenus()).thenReturn(orderMenus);

        WeekendDiscountStrategy strategy = new WeekendDiscountStrategy(orderProcessor, discountEvents);
        strategy.applyDiscount();

        verify(discountEvents, never()).addDiscountEvent(anyString(), anyInt());
    }
}
