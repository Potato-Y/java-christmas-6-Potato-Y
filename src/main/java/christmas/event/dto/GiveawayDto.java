package christmas.event.dto;

import christmas.menu.model.RestaurantMenu;
import christmas.order.model.Quantity;

public class GiveawayDto {

    private final RestaurantMenu restaurantMenu;
    private final Quantity quantity;

    public GiveawayDto(RestaurantMenu restaurantMenu, Quantity quantity) {
        this.restaurantMenu = restaurantMenu;
        this.quantity = quantity;
    }

    public RestaurantMenu getRestaurantMenu() {
        return restaurantMenu;
    }

    public Quantity getQuantity() {
        return quantity;
    }

}
