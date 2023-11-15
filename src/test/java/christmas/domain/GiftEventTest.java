package christmas.domain;

import christmas.domain.events.DiscountEvents;
import christmas.domain.events.GiftEvent;
import christmas.utils.Constants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class GiftEventTest {
    @Test
    @DisplayName("임계값 이상일 경우 증정 이벤트가 적용되어야 한다.")
    public void testGenerateGiftEventOutput_ThresholdExceeded() {
        DiscountEvents mockDiscountEvents = mock(DiscountEvents.class);
        int totalAppliedEventsAmount = 130_000;

        String result = GiftEvent.generateGiftEventOutput(totalAppliedEventsAmount, mockDiscountEvents);

        verify(mockDiscountEvents, times(1)).addDiscountEvent("증정 이벤트", 25_000);
        assertEquals("샴페인 1개", result);
    }

    @Test
    @DisplayName("임계값 미만일 경우 증정 이벤트가 적용되지 않아야 한다.")
    public void testGenerateGiftEventOutput_BelowThreshold() {
        DiscountEvents mockDiscountEvents = mock(DiscountEvents.class);
        int totalAppliedEventsAmount = 100_000;

        String result = GiftEvent.generateGiftEventOutput(totalAppliedEventsAmount, mockDiscountEvents);

        verify(mockDiscountEvents, never()).addDiscountEvent(anyString(), anyInt());
        assertEquals(Constants.NOTHING, result);
    }
}
