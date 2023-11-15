package christmas.order.model;

import static christmas.utils.TestOrderSet.ORDER_FULL_DESERT;
import static christmas.utils.TestOrderSet.ORDER_MAIN_2_DESSERT_3;
import static christmas.utils.TestOrderSet.ORDER_NORMAL_TARGET_TRUE;
import static christmas.utils.TestUtil.ERROR_PREFACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.menu.model.MenuCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
    @ValueSource(strings = {"바비큐립-1,메뉴-1", "아무말", "제로콜라-1", "바비큐립-19,아이스크림-2", "타파스-1,해산물파스타-1,타파스-1"})
    @ParameterizedTest
    void createOrderSheetThrow(String input) {
        assertThatThrownBy(() -> new OrderSheet(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFACE);
    }

    @DisplayName("주문한 가격의 전체 값을 가져온다.")
    @Test
    void getOrderPrice() {
        OrderSheet orderSheet = new OrderSheet(ORDER_NORMAL_TARGET_TRUE.toString());

        assertThat(orderSheet.getOrderPrice())
                .isEqualTo(11_500);
    }

    @DisplayName("주문한 주문서에서 특정 카테고리의 주문 수를 가져온다.")
    @Test
    void getCountToMenuCategory() {
        OrderSheet orderSheet = new OrderSheet(ORDER_FULL_DESERT.toString());

        assertThat(orderSheet.getCountToMenuCategory(MenuCategory.APPETIZER))
                .isEqualTo(ORDER_FULL_DESERT.getAppetizerCount());
        assertThat(orderSheet.getCountToMenuCategory(MenuCategory.MAIN_COURSE))
                .isEqualTo(ORDER_FULL_DESERT.getMainCount());
        assertThat(orderSheet.getCountToMenuCategory(MenuCategory.DESSERT))
                .isEqualTo(ORDER_FULL_DESERT.getDessertCount());
        assertThat(orderSheet.getCountToMenuCategory(MenuCategory.BEVERAGE))
                .isEqualTo(ORDER_FULL_DESERT.getBeverageCount());

    }

    @DisplayName("주문한 주문서에 특정 카테고리가 없으면 0을 반환한다.")
    @Test
    void getCountToMenuCategoryForZero() {
        OrderSheet orderSheet = new OrderSheet(ORDER_MAIN_2_DESSERT_3.toString());

        assertThat(orderSheet.getCountToMenuCategory(MenuCategory.APPETIZER))
                .isEqualTo(0);
        assertThat(orderSheet.getCountToMenuCategory(MenuCategory.BEVERAGE))
                .isEqualTo(0);
    }

}