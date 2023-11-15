package christmas.event.dto;

import christmas.badge.model.EventBadge;
import christmas.event.model.DiscountEvent;
import christmas.menu.model.RestaurantMenu;
import christmas.order.model.Quantity;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class EventResultDto {

    private final Map<DiscountEvent, Integer> discount;

    private final Map<RestaurantMenu, Quantity> giveawayEvent;
    private EventBadge eventBadge;

    public EventResultDto() {
        discount = new HashMap<>();
        giveawayEvent = new HashMap<>();
        eventBadge = EventBadge.NONE;
    }

    public void addGiveawayMenu(GiveawayDto dto) {
        if (dto != null) {
            giveawayEvent.put(dto.getRestaurantMenu(), dto.getQuantity());
        }
    }

    public Map<RestaurantMenu, Quantity> getGiveawayMenu() {
        return giveawayEvent;
    }

    public void addDiscount(DiscountDto dto) {
        if (dto != null) {
            discount.put(dto.getDiscountEvent(), dto.getDiscount());
        }
    }

    public Map<DiscountEvent, Integer> getDiscount() {
        return discount;
    }

    public int getTotalDiscountAmount() {
        return discount.values().stream()
                .mapToInt(discountAmount -> discountAmount)
                .sum();
    }

    public int getTotalGiveawayMenuAmount() {
        int totalAmount = 0;

        for (Entry<RestaurantMenu, Quantity> item : // 증정품 계산
                giveawayEvent.entrySet()) {
            totalAmount += item.getKey().getPrice() * item.getValue().getQuantity();
        }

        return totalAmount;
    }

    public void setEventBadge(EventBadge eventBadge) {
        this.eventBadge = eventBadge;
    }

    public EventBadge getEventBadge() {
        return eventBadge;
    }

}
