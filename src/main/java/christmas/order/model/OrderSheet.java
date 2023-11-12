package christmas.order.model;

import christmas.menu.model.RestaurantMenu;
import christmas.order.util.OrderParser;
import christmas.order.util.OrderValidator;
import java.util.Map;
import java.util.Map.Entry;

public class OrderSheet {

    public static final int MINIMUM_ORDER_QUANTITY = 1;
    public static final int MAXIMUM_ORDER_QUANTITY = 20;

    private final Map<RestaurantMenu, Quantity> orders;

    public OrderSheet(String inOrder) {
        this.orders = OrderParser.parse(inOrder);
        OrderValidator.validateOrder(this.orders);
    }

    public int getOrderPrice() {
        int price = 0;

        for (Entry<RestaurantMenu, Quantity> order :
                orders.entrySet()) {
            price += order.getKey().getPrice() * order.getValue().getQuantity();
        }

        return price;
    }

}
