package christmas.event;

import static christmas.event.EventCalculator.isEventTarget;
import static christmas.event.EventCalculator.runBadgeGrant;
import static christmas.event.EventCalculator.runChristmasDDayDiscount;
import static christmas.event.EventCalculator.runDayOfWeekDiscount;
import static christmas.event.EventCalculator.runGiveawayEvent;
import static christmas.event.EventCalculator.runSpecialDiscount;

import christmas.badge.model.EventBadge;
import christmas.event.dto.EventResultDto;
import christmas.event.model.DayOfMonth;
import christmas.order.model.OrderSheet;

public class EventResultFilter {

    public static EventResultDto eventResult(OrderSheet orderSheet, DayOfMonth dayOfMonth) {
        EventResultDto dto = new EventResultDto(); // 반환할 객체 생성

        if (!isEventTarget(orderSheet)) {
            return dto;
        }

        dto.addGiveawayMenu(runGiveawayEvent(orderSheet)); // 증정 대상 계산
        dto.addDiscount(runChristmasDDayDiscount(dayOfMonth)); // 크리스마스 디데이 할인 계산
        dto.addDiscount(runDayOfWeekDiscount(orderSheet, dayOfMonth)); // 주중 할인 계산
        dto.addDiscount(runSpecialDiscount(dayOfMonth)); // 특별 할인 계산
        EventBadge badge = runBadgeGrant(dto.getTotalDiscountAmount()); // 뱃지 계산
        dto.setEventBadge(badge);

        return dto;
    }

}
