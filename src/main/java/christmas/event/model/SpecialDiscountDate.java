package christmas.event.model;

import java.util.Arrays;

public enum SpecialDiscountDate {
    SPECIAL3(3),
    SPECIAL10(10),
    SPECIAL17(17),
    SPECIAL24(24),
    SPECIAL31(31);

    private final int date;

    SpecialDiscountDate(int date) {
        this.date = date;
    }

    /**
     * date에 대한 값이 있는지 확인한다.
     *
     * @param date 날짜
     * @return 값이 있을 경우 true, 없을 경우 false
     */
    public static boolean contains(int date) {
        return Arrays.stream(SpecialDiscountDate.values())
                .anyMatch(specialDiscountDate -> specialDiscountDate.getDate() == (date));
    }

    public int getDate() {
        return date;
    }
}
