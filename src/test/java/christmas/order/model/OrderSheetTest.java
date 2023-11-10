package christmas.order.model;

import static christmas.utils.TestUtil.ERROR_PREFACE;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderSheetTest {

    @DisplayName("정상적인 주문은 저장")
    @ValueSource(strings = {"양송이수프-2,바비큐립-1,아이스크림-1", "타파스-1,레드와인-1"})
    @ParameterizedTest
    void createOrderSheet(String input) {
        assertThatNoException().isThrownBy(() ->
                new OrderSheet(input));
    }

    @DisplayName("정상적이지 않은 주문은 예외 발생")
    @ValueSource(strings = {"바비큐립-1,메뉴-1", "바베큐립-1,", "아무말", "제로콜라-1"})
    @ParameterizedTest
    void createOrderSheetThrow(String input) {
        assertThatThrownBy(() -> new OrderSheet(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFACE);
    }

}