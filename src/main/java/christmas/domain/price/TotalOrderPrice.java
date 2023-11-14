package christmas.domain.price;

import christmas.domain.menu.Menu;

import java.util.EnumMap;
import java.util.Map;

public class TotalOrderPrice {
    private int totalOrderPrice;

    // calculate가 나을까?
    public int generateTotalOrderPrice(EnumMap<Menu, Integer> orderMenus) {
        int calculatedTotalOrderPrice = 0;

        // EnumMap을 순회하면서 각 메뉴의 가격과 수량을 곱하여 총 가격 계산
        for (Map.Entry<Menu, Integer> menuEntry : orderMenus.entrySet()) {
            Menu menu = menuEntry.getKey();
            int quantity = menuEntry.getValue();
            int menuPrice = menu.getPrice();

            calculatedTotalOrderPrice += menuPrice * quantity;
        }

        this.totalOrderPrice = calculatedTotalOrderPrice;
        return this.totalOrderPrice;
    }

    public String generateTotalOrderPriceOutput() {
        return String.format("%,d원", totalOrderPrice);
    }
}
