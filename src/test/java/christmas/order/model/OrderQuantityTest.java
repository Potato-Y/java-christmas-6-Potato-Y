package christmas.order.model;

import static christmas.utils.TestUtil.ERROR_PREFACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @DisplayName("잘못된 숫자 입력은 오류를 반환한다")
    @ValueSource(strings = {"0", "-10", "21"})
    @ParameterizedTest
    void createQuantityThrow(String input) {
        assertThatThrownBy(() -> new OrderQuantity(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFACE);
    }

}