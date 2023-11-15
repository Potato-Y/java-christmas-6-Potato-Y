package christmas.view;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class InputViewTest extends NsTest {

    @Test
    void 주문_읽기() {
        String text = "해산물파스타-2";
        run(text);
        String input = InputView.readOrder();

        assertThat(output()).contains("주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        assertThat(input).isEqualTo(text);
    }

    @Test
    void 날짜_읽기() {
        String text = "21";
        run(text);
        String input = InputView.readDayOfMonth();

        assertThat(output()).contains("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.",
                "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        assertThat(input).isEqualTo(text);
    }

    @Test
    void 다시_읽기() {
        String message = "우테코";
        run(message);
        String input = InputView.reReadLine();

        assertThat(input).isEqualTo(message);
    }

    @Override
    protected void runMain() {
    }
}