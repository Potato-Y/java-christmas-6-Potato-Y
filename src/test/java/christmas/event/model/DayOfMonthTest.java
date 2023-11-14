package christmas.event.model;

import static christmas.utils.TestUtil.ERROR_PREFACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DayOfMonthTest {

    @DisplayName("정상적인 날짜 저장")
    @ValueSource(strings = {"1", "10", "20"})
    @ParameterizedTest
    void createDayOfMonth(String input) {
        assertThatNoException().isThrownBy(() ->
                new DayOfMonth(input));
    }

    @DisplayName("잘못된 범위의 숫자")
    @ValueSource(strings = {"-10", "0", "32", "40", "일"})
    @ParameterizedTest
    void createOrderSheetThrow(String input) {
        assertThatThrownBy(() -> new DayOfMonth(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFACE);
    }

    @DisplayName("입력한 날짜의 요일을 잘 가져온다.")
    @Test
    void getDayOfWeekSuccess() {
        DayOfMonth day = new DayOfMonth("1");

        assertThat(day.getDayOfWeek()).isEqualTo(Week.FRIDAY);
    }

}