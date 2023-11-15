package christmas.event.model;

import static christmas.utils.TestUtil.ERROR_PREFACE;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WeekTest {

    @DisplayName("요일을 정상적으로 찾아오는지 확인")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7})
    @ParameterizedTest
    void find(int input) {
        assertThatNoException().isThrownBy(() ->
                Week.find(input));
    }

    @DisplayName("잘못된 요일은 예외를 발생한다.")
    @ValueSource(ints = {0, 8, -1, 10})
    @ParameterizedTest
    void findThrow(int input) {
        assertThatThrownBy(() -> Week.find(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFACE);
    }

}