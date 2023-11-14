package christmas.domain.strategy;

import christmas.domain.DiscountEvents;
import christmas.domain.Order;
import christmas.domain.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static org.mockito.Mockito.*;

public class WeekdayDiscountStrategyTest {
    private Order orderProcessor;
    private DiscountEvents discountPrices;

    @BeforeEach
    public void setUp() {
        orderProcessor = mock(Order.class);
        discountPrices = mock(DiscountEvents.class);
    }

    @Test
    @DisplayName("평일 주문 시 디저트 한 개당 2023원이 할인된다.")
    public void testApplyDiscount_WeekdayDessertDiscount() {
        EnumMap<Menu, Integer> orderMenus = new EnumMap<>(Menu.class);
        orderMenus.put(Menu.CHOCOLATE_CAKE, 2);
        orderMenus.put(Menu.SEAFOOD_PASTA, 3);

        when(orderProcessor.getOrderMenus()).thenReturn(orderMenus);

        WeekdayDiscountStrategy strategy = new WeekdayDiscountStrategy(orderProcessor, discountPrices);
        strategy.applyDiscount();

        verify(discountPrices).addDiscountEvent("평일 할인", -4046);
    }

    @Test
    @DisplayName("평일에 디저트를 주문하지 않는 경우 할인 금액은 없다.")
    public void testApplyDiscount_NoWeekdayDessertDiscount() {
        EnumMap<Menu, Integer> orderMenus = new EnumMap<>(Menu.class);
        orderMenus.put(Menu.TAPAS, 3);

        when(orderProcessor.getOrderMenus()).thenReturn(orderMenus);

        WeekdayDiscountStrategy strategy = new WeekdayDiscountStrategy(orderProcessor, discountPrices);
        strategy.applyDiscount();

        verify(discountPrices, never()).addDiscountEvent(anyString(), anyInt());
    }
}
