package christmas.order.model;

import christmas.menu.model.MenuCategory;
import christmas.menu.model.RestaurantMenu;
import christmas.order.util.OrderParser;
import christmas.order.util.OrderValidator;
import java.util.Map;
import java.util.Map.Entry;

public class OrderSheet {

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

    /**
     * 특정 카테고리에 해당하는 메뉴의 수량을 반환
     *
     * @param category
     * @return
     */
    public int getCountToMenuCategory(MenuCategory category) {
        int count = orders.entrySet().stream()
                .filter(order -> order.getKey().getCategory() == category)
                .mapToInt(entry -> entry.getValue().getQuantity())
                .sum();

        return count;
    }

    public Map<RestaurantMenu, Quantity> getOrders() {
        return orders;
    }

}
