package christmas.controller;

import christmas.domain.*;
import christmas.domain.decorator.AdditionalDiscountDecoratorFactory;
import christmas.domain.price.TotalOrderPrice;
import christmas.domain.strategy.BasicDiscountStrategy;
import christmas.domain.strategy.BasicDiscountStrategyFactory;
import christmas.view.InputView;
import christmas.view.OutputView;

import static christmas.domain.OrderProcessor.generateOrderOutput;
import static christmas.view.OutputView.printEventBadge;

// 예외시 재입력
// 원시값 포장 - 날짜, 주문메뉴 - 각 포장 클래스에서 검증, 로직 실행
// plan함수에도 throws 넣어야하나? -> yes. 그냥 exception 말고 세부 예외들 나열해서 throws 적어주는 게 좋음
// 예외 던지는 함수 호출하는 모든 함수에 throws 넣어주기
public class DecemberPromotionController {
    public void run() {
        try {
            planDecemberReservation();
        } catch (Exception exception) {
            OutputView.printExceptionMessage(exception);
        }
    }

    public void planDecemberReservation() {
        int reservationDay = InputView.inputReservationDay();

        // 날짜 검증
        ReservationDayProcesser.validateDay(reservationDay);

        String orderMenus = InputView.inputOrder();

        DiscountEvents discountEvents = new DiscountEvents();

        // EnumMap에 각 메뉴,개수 저장
        OrderProcessor orderProcessor = new OrderProcessor();
        orderProcessor.createOrder(orderMenus);

        BasicDiscountStrategy baseStrategy = BasicDiscountStrategyFactory.createDiscountStrategy(reservationDay, orderProcessor, discountEvents);
        BasicDiscountStrategy strategy = AdditionalDiscountDecoratorFactory.createDiscountStrategy(reservationDay, baseStrategy, discountEvents);

        // 주문 메뉴 출력
        OutputView.printOrderSummary(generateOrderOutput(orderProcessor.getOrderMenus()));

        // 할인 전 총주문 금액
        TotalOrderPrice totalOrder = new TotalOrderPrice();
        int totalOrderPrice = totalOrder.generateTotalOrderPrice(orderProcessor.getOrderMenus());
        OutputView.printTotalOrderPrice(totalOrder.generateTotalOrderPriceOutput());

        // 할인 적용
        if (totalOrderPrice >= 10000) {
            strategy.applyDiscount(); //할인 적용
        }

        // 증정메뉴
        OutputView.printGiftEventSummary(GiftEvent.generateGiftEventOutput(totalOrderPrice, discountEvents));

        // 혜택 내역
        OutputView.printDiscountSummary(discountEvents.generateAllDiscountEventsString());

        // 총 혜택 금액 - 증정이벤트도 있으니 Venefit으로 바꾸기! 실제 할인만 discount로
        int totalDiscountPrice = discountEvents.calculateTotalDiscountPrice();
        OutputView.printTotalBenefits(discountEvents.generateTotalDiscountOutput(totalDiscountPrice));

        // 할인 후 예상 결제 금액 = 주문금액 - 총 할인액(샴페인 비용 제외!)
        int finalPrice = totalOrderPrice - discountEvents.getTotalDiscountPrice();
        OutputView.printFinalPrice(finalPrice);

        // 배지 출력
        printEventBadge(EventBadge.findBadgeByPrice(totalDiscountPrice));
    }
}
