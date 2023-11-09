package christmas.model;

/**
 * 음식 메뉴의 카테고리
 */
public enum MenuCategory {

    APPETIZER("애피타이저"),
    MAIN_COURSE("메인"),
    DESSERT("디저트"),
    BEVERAGE("음료");

    private final String name;

    MenuCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
