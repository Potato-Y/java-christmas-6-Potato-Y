package christmas.event.model;

public enum DiscountEvent {
    CHRISTMAS_D_DAY_DISCOUNT("크리스마스 디데이 할인"),
    WEEKDAY_DISCOUNT("평일 할인"),
    WEEKEND_DISCOUNT("special discount"),
    SPECIAL_DISCOUNT("특별 할인");

    private String name;

    DiscountEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
