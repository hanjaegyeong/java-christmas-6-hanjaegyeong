package christmas.controller;

import christmas.domain.*;
import christmas.domain.decorator.AdditionalDiscountDecoratorFactory;
import christmas.domain.TotalOrderAmount;
import christmas.domain.strategy.BasicDiscountStrategy;
import christmas.domain.strategy.BasicDiscountStrategyFactory;
import christmas.view.InputView;
import christmas.view.OutputView;

public class DecemberPromotionController {
    private static final int MINIMUM_ORDER_AMOUNT_FOR_DISCOUNT = 10000;

    public void run() {
        planDecemberReservation();
    }

    private void planDecemberReservation() {
        int reservationDay = inputReservationDayWithValidation();

        Order order = createAndValidateOrder();

        TotalOrderAmount totalOrderAmountCalculator = new TotalOrderAmount();
        int totalOrderAmount = totalOrderAmountCalculator.calculateTotalOrderAmount(order.getOrderMenus());

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

    private Order createAndValidateOrder() {
        try {
            String orderInput = InputView.inputOrder();
            Order order = new Order();
            order.createOrder(orderInput);
            return order;
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return createAndValidateOrder();
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
        OutputView.printTotalOrderAmount(new TotalOrderAmount().formatTotalOrderAmount());
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
