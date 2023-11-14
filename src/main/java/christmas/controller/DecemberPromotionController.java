package christmas.controller;

import christmas.domain.*;
import christmas.domain.decorator.AdditionalDiscountDecoratorFactory;
import christmas.domain.price.TotalOrderPrice;
import christmas.domain.strategy.BasicDiscountStrategy;
import christmas.domain.strategy.BasicDiscountStrategyFactory;
import christmas.view.InputView;
import christmas.view.OutputView;

public class DecemberPromotionController {
    private static final int MINIMUM_ORDER_AMOUNT_FOR_DISCOUNT = 10000;

    public void run() {
        try {
            planDecemberReservation();
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
        }
    }

    private void planDecemberReservation() {
        int reservationDay = InputView.inputReservationDay();
        ReservationDayProcessor.validateDay(reservationDay);

        Order order = createAndValidateOrder();

        TotalOrderPrice totalOrderPriceCalculator = new TotalOrderPrice();
        int totalOrderPrice = totalOrderPriceCalculator.calculateTotalOrderPrice(order.getOrderMenus());

        DiscountEvents discountEvents = applyDiscountsIfNeeded(order, totalOrderPrice, reservationDay);
        printOrderSummary(order, discountEvents, totalOrderPrice);
    }

    private Order createAndValidateOrder() {
        String orderInput = InputView.inputOrder();
        Order order = new Order();
        order.createOrder(orderInput);
        return order;
    }

    private DiscountEvents applyDiscountsIfNeeded(Order order, int totalOrderPrice, int reservationDay) {
        DiscountEvents discountEvents = new DiscountEvents();
        if (totalOrderPrice >= MINIMUM_ORDER_AMOUNT_FOR_DISCOUNT) {
            BasicDiscountStrategy baseStrategy = BasicDiscountStrategyFactory.createDiscountStrategy(reservationDay, order, discountEvents);
            BasicDiscountStrategy strategy = AdditionalDiscountDecoratorFactory.createDiscountStrategy(reservationDay, baseStrategy, discountEvents);
            strategy.applyDiscount();
        }
        return discountEvents;
    }

    private void printOrderSummary(Order order, DiscountEvents discountEvents, int totalOrderPrice) {
        OutputView.printOrderSummary(Order.generateOrderOutput(order.getOrderMenus()));
        OutputView.printTotalOrderPrice(new TotalOrderPrice().formatTotalOrderPrice());
        OutputView.printGiftEventSummary(GiftEvent.generateGiftEventOutput(totalOrderPrice, discountEvents));
        OutputView.printDiscountSummary(discountEvents.formatAppliedEventsOutput());
        OutputView.printTotalBenefits(discountEvents.formatTotalDiscountAmountOutput());
        OutputView.printFinalPrice(calculateFinalPrice(totalOrderPrice, discountEvents));
        printEventBadge(discountEvents);
    }

    private int calculateFinalPrice(int totalOrderPrice, DiscountEvents discountEvents) {
        return totalOrderPrice - discountEvents.getTotalDiscountAmount();
    }

    private void printEventBadge(DiscountEvents discountEvents) {
        OutputView.printEventBadge(EventBadge.findBadgeByTotalAppliedEventsAmount(discountEvents.getTotalAppliedEventsAmount()));
    }
}
