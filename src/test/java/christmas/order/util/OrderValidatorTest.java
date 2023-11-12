package christmas.order.util;

import static christmas.utils.TestUtil.ERROR_PREFACE;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.menu.model.RestaurantMenu;
import christmas.order.model.Quantity;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderValidatorTest {

    @DisplayName("검증에 통과")
    @Test
    void validateSuccess() {
        // given
        Map<RestaurantMenu, Quantity> orders = new HashMap<>();
        orders.put(RestaurantMenu.SEAFOOD_PASTA, new Quantity("1"));

        // when
        assertThatNoException().isThrownBy(() ->
                OrderValidator.validateOrder(orders));

    }

    @DisplayName("음료만 주문하면 예외가 발생한다.")
    @Test
    void validateThrow1() {
        // given
        Map<RestaurantMenu, Quantity> orders = new HashMap<>();
        orders.put(RestaurantMenu.ZERO_COLA, new Quantity("1"));
        // when, then
        assertThatThrownBy(() -> OrderValidator.validateOrder(orders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFACE);
    }

    @DisplayName("최대 주문 가능한 개수를 넘으면 예외가 발생한다.")
    @Test
    void validateThrow2() {
        // given
        Map<RestaurantMenu, Quantity> orders = new HashMap<>();
        orders.put(RestaurantMenu.CHRISTMAS_PASTA, new Quantity("10"));
        orders.put(RestaurantMenu.SEAFOOD_PASTA, new Quantity("1"));
        orders.put(RestaurantMenu.ZERO_COLA, new Quantity("10"));

        // when, then
        assertThatThrownBy(() -> OrderValidator.validateOrder(orders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFACE);
    }

}