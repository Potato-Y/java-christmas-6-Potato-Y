package christmas.event.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SpecialDiscountDateTest {

    @DisplayName("이벤트 대상 기간인 경우 true가 반환된다.")
    @ValueSource(ints = {3, 10, 17, 24, 31})
    @ParameterizedTest
    void containsTrue(int day) {
        boolean result = SpecialDiscountDate.contains(day);

        assertThat(result).isTrue();
    }

    @DisplayName("이벤트 대상 기간이 아닌 경우 false를 반환한다.")
    @ValueSource(ints = {1, 11, 18, 20})
    @ParameterizedTest
    void containsFalse(int day) {
        boolean result = SpecialDiscountDate.contains(day);

        assertThat(result).isFalse();
    }


}