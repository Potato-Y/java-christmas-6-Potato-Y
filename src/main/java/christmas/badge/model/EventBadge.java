package christmas.badge.model;

/**
 * 이벤트용 배지
 */
public enum EventBadge {

    NONE("없음", 0),
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

    public static EventBadge find(int discountAmount) {
        if (discountAmount > SANTA.getDiscountThreshold()) {
            return SANTA;
        }
        if (discountAmount > TREE.getDiscountThreshold()) {
            return TREE;
        }
        if (discountAmount > STAR.getDiscountThreshold()) {
            return STAR;
        }

        return NONE;
    }

}
