package christmas.view;

import christmas.event.dto.EventResultDto;
import christmas.menu.model.RestaurantMenu;
import christmas.order.model.Quantity;
import christmas.view.util.PrintUtil;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    private static final String NONE = "없음";

    public static void printException(Exception e) {
        PrintUtil.println(e.getMessage());
    }

    public static void printOrderPrice(int orderPrice) {
        PrintUtil.firstPrint("<할인 전 총주문 금액>");
        PrintUtil.printlnWon(orderPrice);
    }

    public static void printDiscountResult(EventResultDto dto) {
        printGiveaway(dto.getGiveawayMenu());
    }

    public static void printGiveaway(Map<RestaurantMenu, Quantity> giveaway) {
        PrintUtil.firstPrint("<증정 메뉴>");

        if (giveaway.isEmpty()) {
            PrintUtil.println(NONE);
            return;
        }

        for (Entry<RestaurantMenu, Quantity> item :
                giveaway.entrySet()) {
            String message = String.format("%s %d개", item.getKey().getName(), item.getValue().getQuantity());
            PrintUtil.println(message);
        }
    }

}
