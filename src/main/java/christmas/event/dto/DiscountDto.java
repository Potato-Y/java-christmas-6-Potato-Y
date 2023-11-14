package christmas.event.dto;

import christmas.event.model.DiscountEvent;

public class DiscountDto {

    private final DiscountEvent discountEvent;
    private final Integer discount;

    public DiscountDto(DiscountEvent discountEvent, int discount) {
        this.discountEvent = discountEvent;
        this.discount = discount;
    }

    public DiscountEvent getDiscountEvent() {
        return discountEvent;
    }

    public Integer getDiscount() {
        return discount;
    }

}
