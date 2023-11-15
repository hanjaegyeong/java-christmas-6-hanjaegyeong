package christmas.domain.order;

import christmas.domain.menu.Menu;
import java.util.EnumMap;

import static christmas.utils.Constants.WON;

public class TotalOrderAmount {
    public static int calculateTotalOrderAmount(EnumMap<Menu, Integer> orderMenus) {
        return orderMenus.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public static String formatTotalOrderAmount(int totalOrderAmount) {
        return String.format("%,d%s", totalOrderAmount, WON);
    }
}
