package christmas.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GiftEventTest {
    private DiscountEvents discountEvents;

    @BeforeEach
    public void setUp() {
        discountEvents = new DiscountEvents();
    }

    @Test
    @DisplayName("총 적용 이벤트 금액이 임계값을 초과하는 경우 증정 이벤트를 생성하고 반환해야 한다.")
    public void testGenerateGiftEventOutput_ThresholdExceeded() {
        int totalAppliedEventsAmount = 150_000;
        String result = GiftEvent.generateGiftEventOutput(totalAppliedEventsAmount, discountEvents);

        assertEquals("샴페인 1개", result);
        assertEquals(25_000, discountEvents.getTotalDiscountAmount());
    }

    @Test
    @DisplayName("총 적용 이벤트 금액이 임계값을 초과하지 않는 경우 없음을  않아야 한다.")
    public void testGenerateGiftEventOutput_ThresholdNotExceeded() {
        int totalAppliedEventsAmount = 10_000;
        String result = GiftEvent.generateGiftEventOutput(totalAppliedEventsAmount, discountEvents);

        assertEquals("없음", result);
        assertEquals(0, discountEvents.getTotalDiscountAmount());
    }
}
