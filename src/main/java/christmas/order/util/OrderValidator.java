package christmas.order.util;

import static christmas.order.model.OrderSize.MAXIMUM_ORDER_QUANTITY;

import christmas.exception.InvalidOrderException;
import christmas.menu.model.MenuCategory;
import christmas.menu.model.RestaurantMenu;
import christmas.order.model.Quantity;
import java.util.Map;
import java.util.Map.Entry;

public class OrderValidator {

    /**
     * 주문에 대한 검증 음료만 주문했는가, 최대 주문 가능 개수를 지켰는가
     *
     * @param orders
     */
    public static void validateOrder(Map<RestaurantMenu, Quantity> orders) {
        validateBeverage(orders);
        validateTotalQuantity(orders);
    }

    /**
     * 음료만 주문하는지 검증한다.
     *
     * @param orders 주문 내용
     */
    private static void validateBeverage(Map<RestaurantMenu, Quantity> orders) {
        boolean onlyBeverages = orders.keySet().stream()
                .allMatch(menu -> menu.getCategory() == MenuCategory.BEVERAGE);

        if (onlyBeverages) {
            throw new InvalidOrderException();
        }
    }

    /**
     * 전체 주문 개수를 검증한다. 최대 주문 가능 개수는 20개이다.
     *
     * @param orders 주문 내용
     */
    private static void validateTotalQuantity(Map<RestaurantMenu, Quantity> orders) {
        int quantity = 0;

        for (Entry<RestaurantMenu, Quantity> order :
                orders.entrySet()) {
            quantity += order.getValue().getQuantity();
        }

        if (quantity > MAXIMUM_ORDER_QUANTITY) {
            throw new InvalidOrderException();
        }
    }

}
