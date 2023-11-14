package christmas.domain.price;

import christmas.domain.menu.Menu;
import java.util.EnumMap;

import static christmas.utils.constants.WON;

public class TotalOrderAmount {
    private int totalOrderAmount;

    public int calculateTotalOrderAmount(EnumMap<Menu, Integer> orderMenus) {
        totalOrderAmount = orderMenus.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();

        return totalOrderAmount;
    }

    public String formatTotalOrderAmount() {
        return String.format("%,d%s", totalOrderAmount, WON);
    }
}
