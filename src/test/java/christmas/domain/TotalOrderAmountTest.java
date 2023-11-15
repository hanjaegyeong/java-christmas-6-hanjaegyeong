package christmas.domain;

import christmas.domain.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TotalOrderAmountTest {
    @Test
    @DisplayName("총 주문 금액 계산 테스트")
    public void calculateTotalOrderAmountTest() {
        EnumMap<Menu, Integer> orderMenus = new EnumMap<>(Menu.class);
        orderMenus.put(Menu.MUSHROOM_SOUP, 2);
        orderMenus.put(Menu.TAPAS, 2);

        int totalAmount = TotalOrderAmount.calculateTotalOrderAmount(orderMenus);

        assertEquals(23_000, totalAmount);
    }

    @Test
    @DisplayName("총 주문 금액 포맷팅 테스트")
    public void formatTotalOrderAmountTest() {
        String formattedAmount = TotalOrderAmount.formatTotalOrderAmount(19000);
        assertEquals("19,000원", formattedAmount);
    }
}
