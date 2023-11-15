package christmas.utils;

public enum TestOrderSet {
    ORDER_NORMAL_TARGET_TRUE("양송이수프-1,타파스-1", 2, 0, 0, 0),
    ORDER_NORMAL_TARGET_FALSE("아이스크림-1", 0, 0, 1, 0),
    ORDER_OVER_GIVEAWAY("해산물파스타-4", 0, 4, 0, 0),
    ORDER_UNDER_GIVEAWAY("해산물파스타-1", 0, 1, 0, 0),
    ORDER_MAIN_2_DESSERT_3("티본스테이크-2,아이스크림-3", 0, 2, 3, 0),
    ORDER_APPETIZER_1("시저샐러드-1", 1, 0, 0, 0);

    private final String order;
    private final int appetizerCount;
    private final int mainCount;
    private final int dessertCount;
    private final int beverageCount;

    TestOrderSet(String order, int appetizerCount, int mainCount, int dessertCount, int beverageCount) {
        this.order = order;
        this.appetizerCount = appetizerCount;
        this.mainCount = mainCount;
        this.dessertCount = dessertCount;
        this.beverageCount = beverageCount;
    }

    @Override
    public String toString() {
        return this.order;
    }

    public int getAppetizerCount() {
        return appetizerCount;
    }

    public int getMainCount() {
        return mainCount;
    }

    public int getDessertCount() {
        return dessertCount;
    }

    public int getBeverageCount() {
        return beverageCount;
    }
}
