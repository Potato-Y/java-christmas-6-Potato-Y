package christmas.order.model;

import static christmas.utils.TestUtil.ERROR_PREFACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderSheetTest {

    @DisplayName("수량이 정상적으로 저장")
    @Test
    void saveQuantitySuccess() {
        // given
        int quantity = 10;

        // when
        OrderQuantity orderQuantity = new OrderQuantity(String.valueOf(quantity));

        // then
        assertThat(orderQuantity.getQuantity())
                .isEqualTo(quantity);
    }

    @DisplayName("수량 범위를 넘어서면 예외가 발생")
    @ValueSource(strings = {"21", "0", "-10", "100"})
    @ParameterizedTest
    void quantityThrow(String input) {
        assertThatThrownBy(() ->
                new OrderQuantity(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFACE);
    }

}