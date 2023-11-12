package christmas.event;

import static christmas.menu.model.RestaurantMenu.CHAMPAGNE;

import christmas.event.dto.EventResultDto;
import christmas.event.model.DayOfMonth;
import christmas.menu.model.RestaurantMenu;
import christmas.order.model.OrderSheet;
import christmas.order.model.Quantity;
import java.util.HashMap;
import java.util.Map;

public class EventCalculatorService {

    private static final int MINIMUM_ORDER_AMOUNT = 10_000;
    private static final int MINIMUM_POINTS_FOR_EVENT = 120_000;

    private EventResultDto dto;

    public EventResultDto calculateEvent(OrderSheet orderSheet, DayOfMonth day) {
        if (!isEventTarget(orderSheet)) {
            return dto;
        }
        this.dto = new EventResultDto();
        runGiveawayEvent(orderSheet); // 증정 검사

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

}
