package christmas.view;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.event.dto.EventResultDto;
import christmas.exception.InvalidDateException;
import christmas.order.model.OrderSheet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OutputViewTest extends NsTest {

    @Test
    void 예외_메시지가_출력된다() {
        try {
            throw new InvalidDateException();
        } catch (Exception e) {
            OutputView.printException(e);
        }

        assertThat(output()).isEqualTo("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @Test
    void 주문_내역_출력() {
        OutputView.printOrderSheet(new OrderSheet("티본스테이크-1,아이스크림-1"));

        assertThat(output()).contains("<주문 메뉴>",
                "티본스테이크 1개",
                "아이스크림 1개");
    }

    @Test
    void 할인_전_총주문_금액() {
        OutputView.printOrderPrice(10_000);

        assertThat(output()).contains("<할인 전 총주문 금액>",
                "10,000원");
    }

    @ValueSource(ints = {1, 2, 23})
    @ParameterizedTest
    void 입력된_날짜가_표시된다(int day) {
        OutputView.printPreviewGuide(day);

        assertThat(output()).isEqualTo("12월 " + day + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    @Test
    void 할인_내용_출력() {
        OrderSheet orderSheet = new OrderSheet("티본스테이크-1,아이스크림-1");
        EventResultDto dto = new EventResultDto();
        OutputView.printDiscountResult(orderSheet, dto);

        OutputView.printDiscountResult(orderSheet, dto);

        assertThat(output()).contains("<증정 메뉴>",
                "없음",
                "<혜택 내역>",
                "<총혜택 금액>",
                "0원",
                "<할인 후 예상 결제 금액>",
                "<12월 이벤트 배지>");
    }

    @Override
    protected void runMain() {
    }

}