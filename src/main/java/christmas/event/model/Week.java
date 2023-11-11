package christmas.event.model;

import christmas.exception.InvalidDateException;
import java.util.Arrays;

public enum Week {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);

    private final int week;

    Week(int week) {
        this.week = week;
    }

    public static Week find(int weekNumber) {
        return Arrays.stream(Week.values())
                .filter(week -> week.week == weekNumber)
                .findAny()
                .orElseThrow(InvalidDateException::new);
    }

}
