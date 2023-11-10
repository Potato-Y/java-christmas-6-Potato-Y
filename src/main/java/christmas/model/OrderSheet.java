package christmas.model;

import christmas.util.OrderParser;
import christmas.util.OrderValidator;
import java.util.Map;

public class OrderSheet {

    public static final int MINIMUM_ORDER_QUANTITY = 1;
    public static final int MAXIMUM_ORDER_QUANTITY = 20;

    private final Map<RestaurantMenu, OrderQuantity> orders;

    public OrderSheet(String inOrder) {
        this.orders = OrderParser.parse(inOrder);
        OrderValidator.validateOrder(this.orders);
    }

}
