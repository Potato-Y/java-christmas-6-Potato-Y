package christmas.event.dto;

import christmas.event.model.DiscountEvent;
import christmas.menu.model.RestaurantMenu;
import christmas.order.model.Quantity;
import java.util.HashMap;
import java.util.Map;

public class EventResultDto {

    private Map<RestaurantMenu, Quantity> giveawayEvent;

    private final Map<DiscountEvent, Integer> discount;

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
}
