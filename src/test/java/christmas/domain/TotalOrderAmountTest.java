package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static christmas.utils.constants.WON;

import christmas.domain.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

public class TotalOrderAmountTest {
    private TotalOrderAmount totalOrderAmount;

    @BeforeEach
    public void setUp() {
        totalOrderAmount = new TotalOrderAmount();
    }

    @Test
    @DisplayName("총 주문 금액 계산 테스트")
    public void testCalculateTotalOrderAmount() {
        EnumMap<Menu, Integer> orderMenus = new EnumMap<>(Menu.class);
        orderMenus.put(Menu.MUSHROOM_SOUP, 2);
        orderMenus.put(Menu.TAPAS, 2);
        orderMenus.put(Menu.ZERO_COLA, 3);

        int expectedTotalAmount = (Menu.MUSHROOM_SOUP.getPrice() * 2) + (Menu.TAPAS.getPrice() * 2) + (Menu.ZERO_COLA.getPrice() * 3);
        int actualTotalAmount = totalOrderAmount.calculateTotalOrderAmount(orderMenus);

        assertEquals(expectedTotalAmount, actualTotalAmount);
    }

    @Test
    @DisplayName("총 주문 금액 형식 테스트")
    public void testFormatTotalOrderAmount() {
        totalOrderAmount.calculateTotalOrderAmount(new EnumMap<>(Menu.class));
        String expectedFormattedAmount = String.format("0%s", WON);
        String actualFormattedAmount = totalOrderAmount.formatTotalOrderAmount();

        assertEquals(expectedFormattedAmount, actualFormattedAmount);
    }
}
