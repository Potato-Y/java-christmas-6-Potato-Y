package christmas.menu.model;

import christmas.exception.InvalidOrderException;
import java.util.Arrays;

/**
 * 식당에서 판매하는 음식 분류, 음식 이름, 음식 가격을 나타냄.
 */
public enum RestaurantMenu {

    BUTTON_MUSHROOM_SOUP(MenuCategory.APPETIZER, "양송이수프", 6_000),
    TAPAS(MenuCategory.APPETIZER, "타파스", 5_500),
    CAESAR_SALAD(MenuCategory.APPETIZER, "시저샐러드", 8_000),
    T_BONE_STEAK(MenuCategory.MAIN_COURSE, "티본스테이크", 55_000),
    BARBECUE_RIBS(MenuCategory.MAIN_COURSE, "바비큐립", 54_000),
    SEAFOOD_PASTA(MenuCategory.MAIN_COURSE, "해산물파스타", 35_000),
    CHRISTMAS_PASTA(MenuCategory.MAIN_COURSE, "크리스마스파스타", 25_000),
    CHOCOLATE_CAKE(MenuCategory.DESSERT, "초코케이크", 15_000),
    ICE_CREAM(MenuCategory.DESSERT, "아이스크림", 5_000),
    ZERO_COLA(MenuCategory.BEVERAGE, "제로콜라", 3_000),
    RED_WINE(MenuCategory.BEVERAGE, "레드와인", 60_000),
    CHAMPAGNE(MenuCategory.BEVERAGE, "샴페인", 25_000);

    private final MenuCategory category;
    private final String name;
    private final int price;

    RestaurantMenu(MenuCategory category, String name, int price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public MenuCategory getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public static RestaurantMenu find(String name) {
        return Arrays.stream(RestaurantMenu.values())
                .filter(menu -> menu.name.equals(name))
                .findAny()
                .orElseThrow(InvalidOrderException::new);
    }

}
