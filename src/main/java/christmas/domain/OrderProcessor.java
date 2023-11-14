package christmas.domain;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuCategory;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class OrderProcessor {
    private static final int MAX_TOTAL_QUANTITY = 20;

    private final EnumMap<Menu, Integer> orderMenus = new EnumMap<>(Menu.class);

    public void createOrder(String userInput) {
        List<String> orders = splitOrders(userInput);

        for (String order : orders) {
            processOrder(order);
        }
    }

    private List<String> splitOrders(String userInput) {
        return Arrays.asList(userInput.split(","));
    }

    private void processOrder(String order) {
        validateOrderFormat(order);

        List<String> menuAndQuantity = splitMenuAndQuantity(order);
        String menuName = extractMenuName(menuAndQuantity);
        int quantity = extractQuantity(menuAndQuantity);

        validateQuantity(quantity);

        Menu menu = findMenuByName(menuName);

        orderMenus.put(menu, quantity);
    }

    private List<String> splitMenuAndQuantity(String order) {
        return Arrays.asList(order.split("-"));
    }

    private String extractMenuName(List<String> menuAndQuantity) {
        return menuAndQuantity.get(0).trim();
    }

    private int extractQuantity(List<String> menuAndQuantity) {
        String quantityString = menuAndQuantity.get(1).trim();
        try {
            return Integer.parseInt(quantityString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("[오류] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private Menu findMenuByName(String menuName) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(menuName)) {
                return menu;
            }
        }
        throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    private void validateOrderFormat(String order) {
        if (!order.contains("-")) {
            throw new IllegalArgumentException("[오류] 주문 형식이 올바르지 않습니다. 메뉴와 수량은 하이픈(-)으로 구분되어야 합니다.");
        }
    }
}