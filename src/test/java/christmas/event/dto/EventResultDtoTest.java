package christmas.event.dto;

import static christmas.event.model.DiscountEvent.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.event.model.DiscountEvent.SPECIAL_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventResultDtoTest {

    @DisplayName("카테고리 수를 맞게 가져온다.")
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

}