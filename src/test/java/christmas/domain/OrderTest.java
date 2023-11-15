package christmas.domain;

import static org.junit.jupiter.api.Assertions.*;

import christmas.exceptions.InvalidOrderException;
import christmas.domain.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTest {
    private Order order;

    @BeforeEach
    public void setUp() {
        order = new Order();
    }

    @Test
    @DisplayName("유효한 주문을 입력할 경우 예외가 발생하지 않아야 한다.")
    public void testCreateOrder_ValidOrder() {
        assertDoesNotThrow(() -> order.createOrder("바비큐립-2,크리스마스파스타-1,제로콜라-3"));
    }

    @Test
    @DisplayName("중복된 메뉴를 주문하면 예외가 발생해야 한다.")
    public void testCreateOrder_DuplicateMenu() {
        assertThrows(InvalidOrderException.class, () -> order.createOrder("바비큐립-2,바비큐립-1,레드와인-3"));
    }

    @Test
    @DisplayName("주문 총 수량 제한을 초과하면 예외가 발생해야 한다.")
    public void testCreateOrder_TotalQuantityLimit() {
        assertThrows(InvalidOrderException.class, () -> order.createOrder("크리스마스파스타-20,제로콜라-2"));
    }

    @Test
    @DisplayName("음료만 주문한 경우 예외가 발생해야 한다.")
    public void testCreateOrder_OnlyDrinkOrders() {
        assertThrows(InvalidOrderException.class, () -> order.createOrder("제로콜라-3,레드와인-2"));
    }

    @Test
    @DisplayName("메뉴명과 수량이 하이픈으로 분리되지 않은 경우 예외가 발생해야 한다.")
    public void testCreateOrder_ValidateOrderFormat_NotExistHyphen() {
        assertThrows(InvalidOrderException.class, () -> order.createOrder("제로콜라3,레드와인2"));
    }

    @Test
    @DisplayName("각 주문이 ,(콤마)로 구분되지 않는 경우 예외가 발생해야 한다.")
    public void testCreateOrder_ValidateOrderFormat_NotExistComma() {
        assertThrows(InvalidOrderException.class, () -> order.createOrder("제로콜라3레드와인2"));
    }

    @Test
    @DisplayName("주문 수량이 1 미만인 경우 예외가 발생해야 한다.")
    public void testCreateOrder_ValidateQuantity() {
        assertThrows(InvalidOrderException.class, () -> order.createOrder("바비큐립-0"));
    }

    @Test
    @DisplayName("메뉴판에 없는 메뉴를 주문 시 예외가 발생해야 한다.")
    public void testCreateOrder_findMenuByName() {
        assertThrows(InvalidOrderException.class, () -> order.createOrder("오징어덮밥-2"));
    }
}
