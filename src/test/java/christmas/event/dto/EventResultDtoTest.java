package christmas.event.dto;

import static christmas.event.model.DiscountEvent.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.event.model.DiscountEvent.SPECIAL_DISCOUNT;
import static christmas.menu.model.RestaurantMenu.CHAMPAGNE;
import static christmas.menu.model.RestaurantMenu.CHOCOLATE_CAKE;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.order.model.Quantity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventResultDtoTest {

    @DisplayName("할인되는 전체 금액을 맞게 가져온다.")
    @Test
    void getTotalDiscountAmount() {
        // given
        EventResultDto dto = new EventResultDto();
        dto.addDiscount(new DiscountDto(CHRISTMAS_D_DAY_DISCOUNT, 1_200));
        dto.addDiscount(new DiscountDto(SPECIAL_DISCOUNT, 1_000));

        // when
        int result = dto.getTotalDiscountAmount();

        // then
        assertThat(result).isEqualTo(2_200);
    }

    @DisplayName("할인 내역이 없으면 0을 반환한다.")
    @Test
    void getTotalDiscountAmountForZero() {
        EventResultDto dto = new EventResultDto();
        assertThat(dto.getTotalDiscountAmount()).isEqualTo(0);
    }

    @DisplayName("할인 내역을 모두 더한다.")
    @Test
    void getTotalGiveawayMenuAmount() {
        EventResultDto dto = new EventResultDto();
        dto.addGiveawayMenu(new GiveawayDto(CHOCOLATE_CAKE, new Quantity("2")));
        dto.addGiveawayMenu(new GiveawayDto(CHAMPAGNE, new Quantity("1")));

        int result = dto.getTotalGiveawayMenuAmount();

        assertThat(result).isEqualTo(CHOCOLATE_CAKE.getPrice() * 2 + CHAMPAGNE.getPrice());
    }

    @DisplayName("증정품 내역이 없으면 0반환한다.")
    @Test
    void getTotalGiveawayMenuAmountForZero() {
        EventResultDto dto = new EventResultDto();
        assertThat(dto.getTotalGiveawayMenuAmount()).isEqualTo(0);
    }

}