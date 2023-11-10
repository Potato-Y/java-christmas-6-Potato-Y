package christmas.order.model;

import static christmas.utils.TestUtil.ERROR_PREFACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderQuantityTest {

    @DisplayName("수량이 저장된다.")
    @Test
    void createOrderQuantity() {
        // given
        final String quantity = "10";

        // when
        OrderQuantity orderQuantity = new OrderQuantity(quantity);

        // then
        assertThat(orderQuantity.getQuantity())
                .isEqualTo(Integer.parseInt(quantity));
    }

    @DisplayName("잘못된 숫자 입력은 오류를 반환한다: 0")
    @Test
    void createQuantityThrow1() {
        assertThatThrownBy(() -> new OrderQuantity("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFACE);
    }

    @DisplayName("잘못된 숫자 입력은 오류를 반환한다: -10")
    @Test
    void createQuantityThrow2() {
        assertThatThrownBy(() -> new OrderQuantity("-10"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFACE);
    }

    @DisplayName("잘못된 숫자 입력은 오류를 반환한다: 21")
    @Test
    void createQuantityThrow3() {
        assertThatThrownBy(() -> new OrderQuantity("21"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFACE);
    }

}