package christmas.event;

import static christmas.event.model.DiscountEvent.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.event.model.DiscountEvent.SPECIAL_DISCOUNT;
import static christmas.event.model.DiscountEvent.WEEKDAY_DISCOUNT;
import static christmas.event.model.DiscountEvent.WEEKEND_DISCOUNT;
import static christmas.menu.model.RestaurantMenu.CHAMPAGNE;
import static christmas.utils.TestOrderSet.ORDER_APPETIZER_1;
import static christmas.utils.TestOrderSet.ORDER_MAIN_2_DESSERT_3;
import static christmas.utils.TestOrderSet.ORDER_NORMAL_TARGET_FALSE;
import static christmas.utils.TestOrderSet.ORDER_NORMAL_TARGET_TRUE;
import static christmas.utils.TestOrderSet.ORDER_OVER_GIVEAWAY;
import static christmas.utils.TestOrderSet.ORDER_UNDER_GIVEAWAY;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.badge.model.EventBadge;
import christmas.event.dto.DiscountDto;
import christmas.event.dto.GiveawayDto;
import christmas.event.model.DayOfMonth;
import christmas.order.model.OrderSheet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EventCalculatorTest {

    @DisplayName("이벤트 대상이면 true를 반환한다.")
    @Test
    void isEventTargetForTrue() {
        // given
        OrderSheet orderSheet = new OrderSheet(ORDER_NORMAL_TARGET_TRUE.toString());
        boolean result = EventCalculator.isEventTarget(orderSheet);

        assertThat(result).isTrue();
    }

    @DisplayName("이벤트 대상이 아니라면 false를 반환한다.")
    @Test
    void isEventTargetForFalse() {
        // given
        OrderSheet orderSheet = new OrderSheet(ORDER_NORMAL_TARGET_FALSE.toString());

        // when
        boolean result = EventCalculator.isEventTarget(orderSheet);

        // than
        assertThat(result).isFalse();
    }

    @DisplayName("주문 금액이 조건 이상이면 샴페인을 반환한다.")
    @Test
    void returnChampagne() {
        // given
        OrderSheet orderSheet = new OrderSheet(ORDER_OVER_GIVEAWAY.toString());

        // when
        GiveawayDto dto = EventCalculator.runGiveawayEvent(orderSheet);

        // then
        assert dto != null;
        assertThat(dto.getRestaurantMenu()).isEqualTo(CHAMPAGNE);
    }

    @DisplayName("주문 금액이 조건 이하이면 dto 객체가 반환되지 않는다.")
    @Test
    void noReturnChampagne() {
        // given
        OrderSheet orderSheet = new OrderSheet(ORDER_UNDER_GIVEAWAY.toString());

        // when
        GiveawayDto dto = EventCalculator.runGiveawayEvent(orderSheet);

        // then
        assertThat(dto).isNull();
    }

    @DisplayName("크리스마스: 날짜에 맞추어 할인율이 증가한다.")
    @ValueSource(strings = {"1", "5", "10", "25"})
    @ParameterizedTest
    void christmasDDayDiscount(String input) {
        // given
        final DayOfMonth day = new DayOfMonth(input);
        final int discount = CHRISTMAS_D_DAY_DISCOUNT.getStartDiscount()
                + (CHRISTMAS_D_DAY_DISCOUNT.getUnit() * (day.getDay() - 1));

        // when
        DiscountDto dto = EventCalculator.runChristmasDDayDiscount(day);

        // then
        assert dto != null;
        assertThat(dto.getDiscountEvent()).isEqualTo(CHRISTMAS_D_DAY_DISCOUNT);
        assertThat(dto.getDiscount()).isEqualTo(discount);
    }

    @DisplayName("크리스마스: 이벤트 날짜가 아니면 dto가 반환되지 않는다.")
    @ValueSource(strings = {"26", "30", "31"})
    @ParameterizedTest
    void noChristmasDDayDiscount(String input) {
        // given
        final DayOfMonth day = new DayOfMonth(input);

        // when
        DiscountDto dto = EventCalculator.runChristmasDDayDiscount(day);

        // then
        assertThat(dto).isNull();
    }

    @DisplayName("주말에는 메인 요리가 개수만큼 할인된다.")
    @Test
    void runDayOfWeekDiscountForWeekend() {
        // given
        DayOfMonth day = new DayOfMonth("2");
        OrderSheet orderSheet = new OrderSheet(ORDER_MAIN_2_DESSERT_3.toString());
        int discount = ORDER_MAIN_2_DESSERT_3.getMainCount() * WEEKEND_DISCOUNT.getUnit();

        // when
        DiscountDto dto = EventCalculator.runDayOfWeekDiscount(orderSheet, day);

        // then
        assert dto != null;
        assertThat(dto.getDiscountEvent()).isEqualTo(WEEKEND_DISCOUNT);
        assertThat(dto.getDiscount()).isEqualTo(discount);
    }

    @DisplayName("평일에는 디저트 요리가 개수만큼 할인된다.")
    @Test
    void runDayOfWeekDiscountForWeekday() {
        // given
        DayOfMonth day = new DayOfMonth("5");
        OrderSheet orderSheet = new OrderSheet(ORDER_MAIN_2_DESSERT_3.toString());
        int discount = ORDER_MAIN_2_DESSERT_3.getDessertCount() * WEEKDAY_DISCOUNT.getUnit();

        // when
        DiscountDto dto = EventCalculator.runDayOfWeekDiscount(orderSheet, day);

        // then
        assert dto != null;
        assertThat(dto.getDiscountEvent()).isEqualTo(WEEKDAY_DISCOUNT);
        assertThat(dto.getDiscount()).isEqualTo(discount);
    }

    @DisplayName("주중,주말 할인에 할인 대상 메뉴가 없는 경우 할인 정보가 반환되지 않는다.")
    @ValueSource(strings = {"2", "12"})
    @ParameterizedTest
    void noDayOfWeekDiscount() {
        // given
        DayOfMonth day = new DayOfMonth("5");
        OrderSheet orderSheet = new OrderSheet(ORDER_APPETIZER_1.toString());

        // when
        DiscountDto dto = EventCalculator.runDayOfWeekDiscount(orderSheet, day);

        // then
        assertThat(dto).isNull();
    }

    @DisplayName("특별 할인날에 할인된다.")
    @ValueSource(strings = {"3", "10", "17", "24", "31"})
    @ParameterizedTest
    void weekdayDiscount(String input) {
        // given
        DayOfMonth day = new DayOfMonth(input);

        // when
        DiscountDto dto = EventCalculator.runSpecialDiscount(day);

        // then
        assert dto != null;
        assertThat(dto.getDiscountEvent()).isEqualTo(SPECIAL_DISCOUNT);
        assertThat(dto.getDiscount()).isEqualTo(SPECIAL_DISCOUNT.getStartDiscount());
    }

    @DisplayName("특별 할인날이 아니면 할인 정보가 반환되지 않는다.")
    @ValueSource(strings = {"1", "30"})
    @ParameterizedTest
    void noWeekdayDiscount(String input) {
        // given
        DayOfMonth day = new DayOfMonth(input);

        // when
        DiscountDto dto = EventCalculator.runSpecialDiscount(day);

        // then
        assertThat(dto).isNull();
    }


    @DisplayName("가격에 맞춰 배지가 발급된다: 없음")
    @Test
    void getNoneBadge() {
        EventBadge badge = EventCalculator.runBadgeGrant(1_000);

        assertThat(badge).isEqualTo(EventBadge.NONE);
    }

    @DisplayName("가격에 맞춰 배지가 발급된다: 별")
    @Test
    void getStarBadge() {
        EventBadge badge = EventCalculator.runBadgeGrant(6_500);

        assertThat(badge).isEqualTo(EventBadge.STAR);
    }

    @DisplayName("가격에 맞춰 배지가 발급된다: 트리")
    @Test
    void getTreeBadge() {
        EventBadge badge = EventCalculator.runBadgeGrant(11_000);

        assertThat(badge).isEqualTo(EventBadge.TREE);
    }

    @DisplayName("가격에 맞춰 배지가 발급된다: 산타")
    @Test
    void getSantaBadge() {
        EventBadge badge = EventCalculator.runBadgeGrant(21_000);

        assertThat(badge).isEqualTo(EventBadge.SANTA);
    }

}