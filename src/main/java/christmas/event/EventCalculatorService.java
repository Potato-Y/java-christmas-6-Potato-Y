package christmas.event;

import static christmas.event.model.DiscountEvent.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.event.model.DiscountEvent.SPECIAL_DISCOUNT;
import static christmas.event.model.DiscountEvent.WEEKDAY_DISCOUNT;
import static christmas.event.model.DiscountEvent.WEEKEND_DISCOUNT;
import static christmas.event.model.Week.FRIDAY;
import static christmas.event.model.Week.SATURDAY;
import static christmas.menu.model.RestaurantMenu.CHAMPAGNE;

import christmas.badge.model.EventBadge;
import christmas.event.dto.EventResultDto;
import christmas.event.model.DayOfMonth;
import christmas.event.model.SpecialDiscountDate;
import christmas.menu.model.MenuCategory;
import christmas.menu.model.RestaurantMenu;
import christmas.order.model.OrderSheet;
import christmas.order.model.Quantity;
import java.util.HashMap;
import java.util.Map;

public class EventCalculatorService {

    private static final int MINIMUM_ORDER_AMOUNT = 10_000;
    private static final int MINIMUM_POINTS_FOR_EVENT = 120_000;
    private static final int CHRISTMAS_DATE = 25;

    private EventResultDto dto;

    public EventResultDto calculateEvent(OrderSheet orderSheet, DayOfMonth day) {
        dto = null;
        if (!isEventTarget(orderSheet)) {
            return dto;
        }
        this.dto = new EventResultDto();
        runGiveawayEvent(orderSheet); // 증정 검사
        runChristmasDDayDiscount(day); // 크리스마스 디데이 할인
        runDayOfWeekDiscount(orderSheet, day);
        runSpecialDiscount(day);
        runBadgeGrant();

        return dto;
    }

    /**
     * 이벤트 대상인지 확인
     *
     * @param orderSheet
     * @return
     */
    private boolean isEventTarget(OrderSheet orderSheet) {
        return orderSheet.getOrderPrice() > MINIMUM_ORDER_AMOUNT;
    }

    /**
     * 주문 금액이 조건 이상일 경우 샴페인을 증정한다.
     *
     * @param orderSheet 주문서
     */
    private void runGiveawayEvent(OrderSheet orderSheet) {
        if (orderSheet.getOrderPrice() > MINIMUM_POINTS_FOR_EVENT) {
            Map<RestaurantMenu, Quantity> giveaway = new HashMap<>() {{
                put(CHAMPAGNE, new Quantity("1"));
            }};

            dto.setGiveawayMenu(giveaway);
        }
    }

    private void runChristmasDDayDiscount(DayOfMonth day) {
        if (day.getDay() > CHRISTMAS_DATE) { // 크리스마스가 지났다면 끝낸다.
            return;
        }

        int price = CHRISTMAS_D_DAY_DISCOUNT.getStartDiscount();
        price += CHRISTMAS_D_DAY_DISCOUNT.getUnit() * (day.getDay() - 1);

        dto.addDiscount(CHRISTMAS_D_DAY_DISCOUNT, price);
    }

    private void runDayOfWeekDiscount(OrderSheet orderSheet, DayOfMonth day) {
        // 주말 할인
        if (day.getDayOfWeek() == FRIDAY || day.getDayOfWeek() == SATURDAY) {
            weekendDiscount(orderSheet);
            return;
        }

        // 평일 할인
        weekdayDiscount(orderSheet);
    }

    private void weekendDiscount(OrderSheet orderSheet) {
        int count = orderSheet.getCountToMenuCategory(MenuCategory.MAIN_COURSE);

        int price = WEEKEND_DISCOUNT.getUnit() * count;

        if (price != 0) {
            dto.addDiscount(WEEKEND_DISCOUNT, price);
        }
    }

    private void weekdayDiscount(OrderSheet orderSheet) {
        int count = orderSheet.getCountToMenuCategory(MenuCategory.DESSERT);

        int price = WEEKDAY_DISCOUNT.getUnit() * count;

        if (price != 0) {
            dto.addDiscount(WEEKDAY_DISCOUNT, price);
        }
    }

    private void runSpecialDiscount(DayOfMonth day) {
        if (SpecialDiscountDate.contains(day.getDay())) {
            dto.addDiscount(SPECIAL_DISCOUNT, SPECIAL_DISCOUNT.getStartDiscount());
        }
    }

    private void runBadgeGrant() {
        EventBadge badge = EventBadge.find(dto.getTotalDiscountAmount());
        dto.setEventBadge(badge);
    }

}
