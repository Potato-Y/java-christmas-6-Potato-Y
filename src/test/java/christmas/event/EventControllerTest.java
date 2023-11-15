package christmas.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.event.model.DayOfMonth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventControllerTest extends NsTest {

    EventController controller;

    @BeforeEach
    void setup() {
        this.controller = new EventController();
    }

    @DisplayName("사용자로부터 날짜를 입력받는다.")
    @Test
    void readDayOfMonth() {
        DayOfMonth inputDay = new DayOfMonth("21");
        run(String.valueOf(inputDay.getDay()));

        assertThatNoException().isThrownBy(() -> {
            DayOfMonth day = controller.readDayOfMonth();
            assertThat(day.getDay()).isEqualTo(inputDay.getDay());
        });
    }

    @DisplayName("잘못된 날짜는 다시 입력받는다.")
    @Test
    void reReadDayOfMonth() {
        run("32", "31");

        controller.readDayOfMonth();
        assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    // calculateEvent는 단순 데이터 요청으로 EventResultFilterTest에서 테스트됨.

    @Override
    protected void runMain() {
    }

}