package christmas.model;

/**
 * 이벤트용 배지
 */
public enum EventBadge {

    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int discountThreshold;

    EventBadge(String name, int discountThreshold) {
        this.name = name;
        this.discountThreshold = discountThreshold;
    }

    public String getName() {
        return name;
    }

    public int getDiscountThreshold() {
        return discountThreshold;
    }

}
