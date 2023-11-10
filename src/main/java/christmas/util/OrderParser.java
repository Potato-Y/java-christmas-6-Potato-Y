package christmas.util;

import static christmas.model.Separator.COMMA;
import static christmas.model.Separator.DASH;

import christmas.exception.InvalidOrderException;
import christmas.model.OrderQuantity;
import christmas.model.RestaurantMenu;
import java.util.HashMap;
import java.util.Map;

public class OrderParser {

    /**
     * 양식에 맞추어 들어온 주문을 <메뉴, 수량> 형식으로 변환
     *
     * @param inOrder 메뉴명1-2,메뉴2-1, ...
     * @return Map<RestaurantMenu, OrderQuantity>
     */
    public static Map<RestaurantMenu, OrderQuantity> parse(String inOrder) {
        Map<RestaurantMenu, OrderQuantity> totalOrder = new HashMap<>();

        String[] orders = inOrder.split(COMMA.toString()); // ,기준으로 자름. 메뉴명-수량
        for (String order :
                orders) {
            String[] dashSeparation = order.split(DASH.toString()); // [메뉴명, 수량]
            RestaurantMenu menu = RestaurantMenu.find(dashSeparation[0]); // 메뉴
            OrderQuantity quantity = new OrderQuantity(dashSeparation[1]); // 수량

            totalOrder.put(menu, quantity);
        }

        checkSize(totalOrder.size(), orders.length); // 중복 메뉴 여부를 확인한다.
        return totalOrder;
    }

    /**
     * 두 값을 비교하고, 다른 경우 예외를 발생한다.
     *
     * @param orderSize 전체 오더 개수
     * @param splitSize 자른 오더 개수
     */
    private static void checkSize(int orderSize, int splitSize) {
        if (orderSize != splitSize) {
            throw new InvalidOrderException();
        }
    }
}
