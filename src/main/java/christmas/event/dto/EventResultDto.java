package christmas.event.dto;

import christmas.badge.model.EventBadge;
import christmas.event.model.DiscountEvent;
import christmas.menu.model.RestaurantMenu;
import christmas.order.model.Quantity;
import java.util.HashMap;
import java.util.Map;

public class EventResultDto {

    private final Map<DiscountEvent, Integer> discount;

    private Map<RestaurantMenu, Quantity> giveawayEvent;
    private EventBadge eventBadge;

    public EventResultDto() {
        discount = new HashMap<>();
    }

    public void setGiveawayMenu(Map<RestaurantMenu, Quantity> giveawayEvent) {
        this.giveawayEvent = giveawayEvent;
    }

    public Map<RestaurantMenu, Quantity> getGiveawayMenu() {
        return giveawayEvent;
    }

    public void addDiscount(DiscountEvent event, int price) {
        discount.put(event, price);
    }

    public Map<DiscountEvent, Integer> getDiscount() {
        return discount;
    }

    public int getTotalDiscountAmount() {
        return discount.values().stream()
                .mapToInt(discountAmount -> discountAmount)
                .sum();
    }

    public void setEventBadge(EventBadge eventBadge) {
        this.eventBadge = eventBadge;
    }

    public EventBadge getEventBadge() {
        return eventBadge;
    }

}
