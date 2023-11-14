package christmas.domain.price;

import christmas.domain.menu.Menu;
import java.util.EnumMap;

import static christmas.utils.constants.WON;

public class TotalOrderPrice {
    private int totalOrderPrice;

    public int calculateTotalOrderPrice(EnumMap<Menu, Integer> orderMenus) {
        totalOrderPrice = orderMenus.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();

        return totalOrderPrice;
    }

    public String formatTotalOrderPrice() {
        return String.format("%,d%s", totalOrderPrice, WON);
    }
}
