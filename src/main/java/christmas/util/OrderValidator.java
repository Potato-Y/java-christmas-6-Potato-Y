package christmas.util;

import static christmas.model.MenuCategory.APPETIZER;
import static christmas.model.MenuCategory.DESSERT;
import static christmas.model.MenuCategory.MAIN_COURSE;
import static christmas.model.OrderSheet.MAXIMUM_ORDER_QUANTITY;

import christmas.exception.InvalidOrderException;
import christmas.model.OrderQuantity;
import christmas.model.RestaurantMenu;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class OrderValidator {

    /**
     * 주문에 대한 검증 음료만 주문했는가, 최대 주문 가능 개수를 지켰는가
     *
     * @param orders
     */
    public static void validateOrder(Map<RestaurantMenu, OrderQuantity> orders) {
        validateBeverage(orders);
        validateTotalQuantity(orders);
    }

    /**
     * 음료만 주문하는지 검증한다.
     *
     * @param orders 주문 내용
     */
    private static void validateBeverage(Map<RestaurantMenu, OrderQuantity> orders) {
        Set<RestaurantMenu> menus = orders.keySet();

        if (menus.contains(APPETIZER)) {
            return;
        }
        if (menus.contains(MAIN_COURSE)) {
            return;
        }
        if (menus.contains(DESSERT)) {
            return;
        }

        throw new InvalidOrderException();
    }

    /**
     * 전체 주문 개수를 검증한다. 최대 주문 가능 개수는 20개이다.
     *
     * @param orders 주문 내용
     */
    private static void validateTotalQuantity(Map<RestaurantMenu, OrderQuantity> orders) {
        int quantity = 0;

        for (Entry<RestaurantMenu, OrderQuantity> order :
                orders.entrySet()) {
            quantity += order.getValue().getQuantity();
        }

        if (quantity > MAXIMUM_ORDER_QUANTITY) {
            throw new InvalidOrderException();
        }
    }
}
