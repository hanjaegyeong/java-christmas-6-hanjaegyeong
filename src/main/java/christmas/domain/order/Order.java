package christmas.domain.order;

import christmas.exceptions.InvalidOrderException;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuCategory;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public class Order {
    private static final int MAX_TOTAL_QUANTITY = 20;
    private static final String INVALID_ORDER_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String ORDER_SEPARATOR = "-";
    private static final String QUANTITY_SUFFIX = "개";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private final EnumMap<Menu, Integer> orderMenus = new EnumMap<>(Menu.class);
    private int totalQuantity = 0;

    public void createOrder(String userInput) {
        List<String> orders = splitOrders(userInput);

        for (String order : orders) {
            processOrder(order);
        }

        validateTotalQuantity();
        preventOnlyDrinkOrders();
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
        checkForDuplicateMenu(menu);

        orderMenus.put(menu, quantity);
        totalQuantity += quantity;
    }

    private List<String> splitMenuAndQuantity(String order) {
        return Arrays.asList(order.split(ORDER_SEPARATOR));
    }

    private String extractMenuName(List<String> menuAndQuantity) {
        return menuAndQuantity.get(0).trim();
    }

    private int extractQuantity(List<String> menuAndQuantity) {
        String quantityString = menuAndQuantity.get(1).trim();
        try {
            return Integer.parseInt(quantityString);
        } catch (NumberFormatException e) {
            throw new InvalidOrderException(INVALID_ORDER_MESSAGE);
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < 1) {
            throw new InvalidOrderException(INVALID_ORDER_MESSAGE);
        }
    }

    private void checkForDuplicateMenu(Menu menu) {
        if (orderMenus.containsKey(menu)) {
            throw new InvalidOrderException(INVALID_ORDER_MESSAGE);
        }
    }

    private Menu findMenuByName(String menuName) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(menuName)) {
                return menu;
            }
        }
        throw new InvalidOrderException(INVALID_ORDER_MESSAGE);
    }

    private void validateOrderFormat(String order) {
        if (!order.contains("-")) {
            throw new InvalidOrderException(INVALID_ORDER_MESSAGE);
        }
    }

    private void validateTotalQuantity() {
        if (totalQuantity > MAX_TOTAL_QUANTITY) {
            throw new InvalidOrderException(INVALID_ORDER_MESSAGE);
        }
    }

    private void preventOnlyDrinkOrders() {
        boolean onlyDrinks = orderMenus.keySet().stream()
                .allMatch(menu -> menu.getCategory() == MenuCategory.DRINK);
        if (onlyDrinks) {
            throw new InvalidOrderException(INVALID_ORDER_MESSAGE);
        }
    }

    public static String generateOrderOutput(EnumMap<Menu, Integer> orderMap) {
        return orderMap.entrySet().stream()
                .map(entry -> entry.getKey().getName() + " " + entry.getValue() + QUANTITY_SUFFIX)
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    public EnumMap<Menu, Integer> getOrderMenus() {
        return orderMenus;
    }
}
