package christmas.view.util;

import christmas.event.model.DiscountEvent;
import christmas.menu.model.RestaurantMenu;
import christmas.order.model.Quantity;
import java.util.Map;
import java.util.Map.Entry;

public class BenefitCalculation {

    public static int calculateTotalBenefits(Map<RestaurantMenu, Quantity> giveawayMenu,
                                             Map<DiscountEvent, Integer> discount) {
        int totalDiscount = 0;

        for (Entry<RestaurantMenu, Quantity> item : // 증정품 계산
                giveawayMenu.entrySet()) {
            totalDiscount += item.getKey().getPrice() * item.getValue().getQuantity();
        }
        for (Entry<DiscountEvent, Integer> item :  // 할인액 더하기
                discount.entrySet()) {
            totalDiscount += item.getValue();
        }

        return totalDiscount;
    }


    public static int calculateDiscount(Map<DiscountEvent, Integer> discount) {
        return discount.values().stream()
                .mapToInt(integer -> integer)
                .sum();
    }
}
