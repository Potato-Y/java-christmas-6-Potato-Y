package christmas.event;

import static christmas.event.model.DiscountEvent.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.event.model.DiscountEvent.SPECIAL_DISCOUNT;
import static christmas.event.model.DiscountEvent.WEEKDAY_DISCOUNT;
import static christmas.event.model.DiscountEvent.WEEKEND_DISCOUNT;
import static christmas.event.model.Week.FRIDAY;
import static christmas.event.model.Week.SATURDAY;
import static christmas.menu.model.RestaurantMenu.CHAMPAGNE;

import christmas.badge.model.EventBadge;
import christmas.event.dto.DiscountDto;
import christmas.event.dto.GiveawayDto;
import christmas.event.model.DayOfMonth;
import christmas.event.model.SpecialDiscountDate;
import christmas.menu.model.MenuCategory;
import christmas.order.model.OrderSheet;
import christmas.order.model.Quantity;

public class EventCalculator {

    private static final int MINIMUM_ORDER_AMOUNT = 10_000;
    private static final int MINIMUM_POINTS_FOR_EVENT = 120_000;
    private static final int CHRISTMAS_DATE = 25;

    /**
     * 이벤트 대상인지 확인
     *
     * @param orderSheet
     * @return
     */
    public static boolean isEventTarget(OrderSheet orderSheet) {
        return orderSheet.getOrderPrice() > MINIMUM_ORDER_AMOUNT;
    }

    /**
     * 주문 금액이 조건 이상일 경우 샴페인을 증정한다.
     *
     * @param orderSheet 주문서
     * @return
     */
    public static GiveawayDto runGiveawayEvent(OrderSheet orderSheet) {
        if (orderSheet.getOrderPrice() > MINIMUM_POINTS_FOR_EVENT) {
            return new GiveawayDto(CHAMPAGNE, new Quantity("1"));
        }

        return null;
    }

    public static DiscountDto runChristmasDDayDiscount(DayOfMonth day) {
        if (day.getDay() > CHRISTMAS_DATE) { // 크리스마스가 지났다면 끝낸다.
            return null;
        }

        int price = CHRISTMAS_D_DAY_DISCOUNT.getStartDiscount();
        price += CHRISTMAS_D_DAY_DISCOUNT.getUnit() * (day.getDay() - 1);

        return new DiscountDto(CHRISTMAS_D_DAY_DISCOUNT, price);
    }

    public static DiscountDto runDayOfWeekDiscount(OrderSheet orderSheet, DayOfMonth day) {
        // 주말 할인
        if (day.getDayOfWeek() == FRIDAY || day.getDayOfWeek() == SATURDAY) {
            return weekendDiscount(orderSheet);
        }

        // 평일 할인
        return weekdayDiscount(orderSheet);
    }

    private static DiscountDto weekendDiscount(OrderSheet orderSheet) {
        int count = orderSheet.getCountToMenuCategory(MenuCategory.MAIN_COURSE);

        int price = WEEKEND_DISCOUNT.getUnit() * count;

        if (price != 0) {
            return new DiscountDto(WEEKEND_DISCOUNT, price);
        }
        return null;
    }

    private static DiscountDto weekdayDiscount(OrderSheet orderSheet) {
        int count = orderSheet.getCountToMenuCategory(MenuCategory.DESSERT);

        int price = WEEKDAY_DISCOUNT.getUnit() * count;

        if (price != 0) {
            return new DiscountDto(WEEKDAY_DISCOUNT, price);
        }
        return null;
    }

    public static DiscountDto runSpecialDiscount(DayOfMonth day) {
        if (SpecialDiscountDate.contains(day.getDay())) {
            return new DiscountDto(SPECIAL_DISCOUNT, SPECIAL_DISCOUNT.getStartDiscount());
        }
        return null;
    }

    public static EventBadge runBadgeGrant(int totalDiscountAmount) {
        EventBadge badge = EventBadge.find(totalDiscountAmount);
        return badge;
    }

}
