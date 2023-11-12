package christmas.order.util;

import static christmas.utils.TestUtil.ERROR_PREFACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.menu.model.RestaurantMenu;
import christmas.order.model.Quantity;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderParserTest {

    @DisplayName("문자열을 메뉴로 변환한다.")
    @Test
    void parseSuccess() {
        String userOrder = "양송이수프-1,해산물파스타-2,초코케이크-1,제로콜라-3";
        Map<RestaurantMenu, Quantity> order = OrderParser.parse(userOrder);

        assertThat(order.containsKey(RestaurantMenu.BUTTON_MUSHROOM_SOUP)).isTrue();
        assertThat(order.get(RestaurantMenu.BUTTON_MUSHROOM_SOUP).getQuantity()).isEqualTo(1);

        assertThat(order.containsKey(RestaurantMenu.SEAFOOD_PASTA)).isTrue();
        assertThat(order.get(RestaurantMenu.SEAFOOD_PASTA).getQuantity()).isEqualTo(2);

        assertThat(order.containsKey(RestaurantMenu.CHOCOLATE_CAKE)).isTrue();
        assertThat(order.get(RestaurantMenu.CHOCOLATE_CAKE).getQuantity()).isEqualTo(1);

        assertThat(order.containsKey(RestaurantMenu.ZERO_COLA)).isTrue();
        assertThat(order.get(RestaurantMenu.ZERO_COLA).getQuantity()).isEqualTo(3);
    }

    @DisplayName("중복된 메뉴는 입력할 수 없다.")
    @Test
    void parseThrow() {
        String userOrder = "양송이수프-1,양송이수프-2";

        assertThatThrownBy(() -> OrderParser.parse(userOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFACE);
    }

}