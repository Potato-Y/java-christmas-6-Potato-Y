package christmas.order;

import static christmas.utils.TestUtil.ERROR_PREFACE;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.order.model.OrderSheet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderControllerTest extends NsTest {

    OrderController orderController;

    @BeforeEach
    void setup() {
        this.orderController = new OrderController();
    }

    @DisplayName("사용자에게 메뉴 입력을 받고 주문서를 반환한다.")
    @Test
    void readOrder() {
        run("양송이수프-2,바비큐립-1,아이스크림-1");

        OrderSheet orderSheet = orderController.readOrder();

        assertThat(orderSheet).isNotNull();
    }

    @DisplayName("잘못된 메뉴는 다시 입력해야 한다.")
    @Test
    void readOrderThrow() {
        run("제로콜라-2", "양송이수프-2");

        OrderSheet orderSheet = orderController.readOrder();

        assertThat(output()).contains(ERROR_PREFACE);
        assertThat(orderSheet).isNotNull();
    }

    @Override
    protected void runMain() {
    }

}