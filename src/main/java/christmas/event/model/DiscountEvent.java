package christmas.event.model;

public enum DiscountEvent {
    CHRISTMAS_D_DAY_DISCOUNT("크리스마스 디데이 할인", 100, 1_000),
    WEEKDAY_DISCOUNT("평일 할인", 2_023, 0),
    WEEKEND_DISCOUNT("주말 할인", 2_023, 0),
    SPECIAL_DISCOUNT("특별 할인", 0, 1_000);

    private String name;
    private int unit;
    private int startDiscount;

    DiscountEvent(String name, int unit, int startDiscount) {
        this.name = name;
        this.unit = unit;
        this.startDiscount = startDiscount;
    }

    public String getName() {
        return name;
    }

    public int getUnit() {
        return unit;
    }

    public int getStartDiscount() {
        return startDiscount;
    }

}
