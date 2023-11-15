package christmas.event;

import static christmas.badge.model.EventBadge.NONE;
import static christmas.badge.model.EventBadge.SANTA;
import static christmas.event.model.DiscountEvent.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.event.model.DiscountEvent.SPECIAL_DISCOUNT;
import static christmas.event.model.DiscountEvent.WEEKDAY_DISCOUNT;
import static christmas.event.model.DiscountEvent.WEEKEND_DISCOUNT;
import static christmas.menu.model.RestaurantMenu.CHAMPAGNE;
import static christmas.utils.TestOrderSet.ORDER_FULL;
import static christmas.utils.TestOrderSet.ORDER_NORMAL_TARGET_FALSE;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.event.dto.EventResultDto;
import christmas.event.model.DayOfMonth;
import christmas.order.model.OrderSheet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventResultFilterTest {

    @DisplayName("할인 전체 로직을 거친다.")
    @Test
    void allEventCalculate() {
        // given
        OrderSheet orderSheet = new OrderSheet(ORDER_FULL.toString());
        DayOfMonth day = new DayOfMonth("10");

        // when
        EventResultDto dto = EventResultFilter.eventResult(orderSheet, day);

        // then
        assertThat(dto.getDiscount().containsKey(CHRISTMAS_D_DAY_DISCOUNT)).isTrue();
        assertThat(dto.getDiscount().containsKey(WEEKDAY_DISCOUNT)).isTrue();
        assertThat(dto.getDiscount().containsKey(WEEKEND_DISCOUNT)).isTrue();
        assertThat(dto.getDiscount().containsKey(SPECIAL_DISCOUNT)).isTrue();
        assertThat(dto.getGiveawayMenu().containsKey(CHAMPAGNE)).isTrue();
        assertThat(dto.getEventBadge()).isEqualTo(SANTA);
    }

    @DisplayName("할인 조건이 맞지 않으면 필터를 거치지 않는다.")
    @Test
    void noEventCalculate() {
        // given
        OrderSheet orderSheet = new OrderSheet(ORDER_NORMAL_TARGET_FALSE.toString());
        DayOfMonth day = new DayOfMonth("10");

        // when
        EventResultDto dto = EventResultFilter.eventResult(orderSheet, day);

        // then
        assertThat(dto.getDiscount().isEmpty()).isTrue();
        assertThat(dto.getGiveawayMenu().isEmpty()).isTrue();
        assertThat(dto.getEventBadge()).isEqualTo(NONE);
    }
}