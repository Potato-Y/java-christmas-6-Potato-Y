package christmas.view.util;

import christmas.menu.model.RestaurantMenu;
import christmas.order.model.Quantity;
import java.util.Map;
import java.util.Map.Entry;

public class BenefitCalculation {

    public static int calculateTotalBenefits(Map<RestaurantMenu, Quantity> giveawayMenu,
                                             int discount) {
        int totalDiscount = 0;

        for (Entry<RestaurantMenu, Quantity> item : // 증정품 계산
                giveawayMenu.entrySet()) {
            totalDiscount += item.getKey().getPrice() * item.getValue().getQuantity();
        }
        totalDiscount += discount;

        return totalDiscount;
    }

}
