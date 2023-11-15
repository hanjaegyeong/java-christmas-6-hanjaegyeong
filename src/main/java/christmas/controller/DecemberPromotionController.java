package christmas.controller;

import christmas.domain.events.DiscountEvents;
import christmas.domain.events.EventBadge;
import christmas.domain.events.GiftEvent;
import christmas.domain.events.discounts.decorators.AdditionalDiscountDecoratorFactory;
import christmas.domain.order.FinalAmount;
import christmas.domain.order.TotalOrderAmount;
import christmas.domain.events.discounts.strategies.BasicDiscountStrategy;
import christmas.domain.events.discounts.strategies.BasicDiscountStrategyFactory;
import christmas.domain.order.Order;
import christmas.domain.order.ReservationDayProcessor;
import christmas.view.InputView;
import christmas.view.OutputView;

public class DecemberPromotionController {
    private static final int MINIMUM_ORDER_AMOUNT_FOR_DISCOUNT = 10_000;

    public void run() {
        planDecemberReservation();
    }

    private void planDecemberReservation() {
        int reservationDay = inputReservationDayWithValidation();

        Order order = inputAndCreateOrderWithValidation();
        int totalOrderAmount = TotalOrderAmount.calculateTotalOrderAmount(order.getOrderMenus());

        DiscountEvents discountEvents = applyDiscountsIfNeeded(order, totalOrderAmount, reservationDay);
        printOrderSummary(order, discountEvents, totalOrderAmount);
    }

    private static int inputReservationDayWithValidation() {
        try {
            int reservationDay = InputView.inputReservationDay();
            ReservationDayProcessor.validateDay(reservationDay);
            return reservationDay;
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return inputReservationDayWithValidation();
        }
    }

    private Order inputAndCreateOrderWithValidation() {
        try {
            String orderInput = InputView.inputOrder();
            Order order = new Order();
            order.createOrder(orderInput);
            return order;
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return inputAndCreateOrderWithValidation();
        }
    }

    private DiscountEvents applyDiscountsIfNeeded(Order order, int totalOrderAmount, int reservationDay) {
        DiscountEvents discountEvents = new DiscountEvents();
        if (totalOrderAmount >= MINIMUM_ORDER_AMOUNT_FOR_DISCOUNT) {
            BasicDiscountStrategy baseStrategy = BasicDiscountStrategyFactory.createDiscountStrategy(reservationDay, order, discountEvents);
            BasicDiscountStrategy strategy = AdditionalDiscountDecoratorFactory.createDiscountStrategy(reservationDay, baseStrategy, discountEvents);
            strategy.applyDiscount();
        }
        return discountEvents;
    }

    private void printOrderSummary(Order order, DiscountEvents discountEvents, int totalOrderAmount) {
        OutputView.printOrderSummary(Order.generateOrderOutput(order.getOrderMenus()));
        OutputView.printTotalOrderAmount(TotalOrderAmount.formatTotalOrderAmount(totalOrderAmount));
        OutputView.printGiftEventSummary(GiftEvent.generateGiftEventOutput(totalOrderAmount, discountEvents));
        OutputView.printDiscountSummary(discountEvents.formatAppliedEventsOutput());
        OutputView.printTotalBenefits(discountEvents.formatTotalDiscountAmountOutput());
        OutputView.printFinalAmount(FinalAmount.calculateAndFormatFinalAmountOutput(totalOrderAmount, discountEvents));
        printEventBadge(discountEvents);
    }

    private void printEventBadge(DiscountEvents discountEvents) {
        OutputView.printEventBadge(EventBadge.findBadgeByTotalAppliedEventsAmount(discountEvents.getTotalAppliedEventsAmount()));
    }
}
