package christmas.event.dto;

import christmas.menu.model.RestaurantMenu;
import christmas.order.model.Quantity;
import java.util.Map;

public class EventResultDto {

    private Map<RestaurantMenu, Quantity> giveawayEvent;

    public void setGiveawayMenu(Map<RestaurantMenu, Quantity> giveawayEvent) {
        this.giveawayEvent = giveawayEvent;
    }

    public Map<RestaurantMenu, Quantity> getGiveawayMenu() {
        return giveawayEvent;
    }

}
