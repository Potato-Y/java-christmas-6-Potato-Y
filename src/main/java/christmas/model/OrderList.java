package christmas.model;

import static christmas.model.Separator.COMMA;
import static christmas.model.Separator.DASH;

import java.util.HashMap;
import java.util.Map;

public class OrderList {

    private final Map<RestaurantMenu, OrderQuantity> orders;

    public OrderList(String inOrder) {
        Map<RestaurantMenu, OrderQuantity> orders = conversionOrders(inOrder);

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
            // todo 검증
            totalOrder.put(menu, quantity);
        }

        return totalOrder;
    }


}
