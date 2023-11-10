package christmas.model;

import static christmas.model.MenuCategory.APPETIZER;
import static christmas.model.MenuCategory.DESSERT;
import static christmas.model.MenuCategory.MAIN_COURSE;
import static christmas.model.OrderQuantity.MAXIMUM_ORDER_QUANTITY;
import static christmas.model.Separator.COMMA;
import static christmas.model.Separator.DASH;

import christmas.exception.InvalidOrderException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class OrderSheet {

    private final Map<RestaurantMenu, OrderQuantity> orders;

    public OrderSheet(String inOrder) {
        Map<RestaurantMenu, OrderQuantity> orders = conversionOrders(inOrder);
        validateBeverage(orders);
        validateTotalQuantity(orders);

        this.orders = orders;
    }

    /**
     * 양식에 맞추어 들어온 주문을 <메뉴, 수량> 형식으로 변환
     *
     * @param inOrder 메뉴명1-2,메뉴2-1, ...
     * @return Map<RestaurantMenu, OrderQuantity>
     */
    private Map<RestaurantMenu, OrderQuantity> conversionOrders(String inOrder) {
        Map<RestaurantMenu, OrderQuantity> totalOrder = new HashMap<>();

        String[] orders = inOrder.split(COMMA.toString()); // ,기준으로 자름. 메뉴명-수량
        for (String order :
                orders) {
            String[] dashSeparation = order.split(DASH.toString()); // [메뉴명, 수량]
            RestaurantMenu menu = RestaurantMenu.find(dashSeparation[0]); // 메뉴
            OrderQuantity quantity = new OrderQuantity(dashSeparation[1]); // 수량

            totalOrder.put(menu, quantity);
        }

        validateDuplicateMenu(totalOrder, orders.length); // 중복 메뉴 여부를 확인한다.
        return totalOrder;
    }

    private void validateDuplicateMenu(Map<RestaurantMenu, OrderQuantity> orders, int length) {
        if (orders.size() != length) {
            throw new InvalidOrderException();
        }
    }

    /**
     * 음료만 주문하는지 검증한다.
     *
     * @param orders 주문 내용
     */
    private void validateBeverage(Map<RestaurantMenu, OrderQuantity> orders) {
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
     * 전체 주문 개수를 검증한다. 최대 주문 가능 액수는 20개이다.
     *
     * @param orders 주문 내용
     */
    private void validateTotalQuantity(Map<RestaurantMenu, OrderQuantity> orders) {
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
