package christmas.order.model;

import static christmas.utils.TestUtil.ERROR_PREFACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @DisplayName("최대 수량을 넘어서면 예외가 발생")
    @Test
    void quantityThrowForOver() {
        assertThatThrownBy(() ->
                new OrderQuantity("21"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFACE);
    }

    @DisplayName("수량이 1보다 적으면 예외가 발생")
    @Test
    void quantityThrowForDown() {
        assertThatThrownBy(() ->
                new OrderQuantity("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFACE);
    }

}