package christmas.event.model;

import christmas.exception.InvalidDateException;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class DayOfMonth {

    private static final int ORDER_YEAR = 2023;
    private static final int ORDER_MONTH = 12;
    private static final int START_DATE = 1;
    private static final int END_DATE = 31;

    private int day;

    public DayOfMonth(String day) {
        this.day = convertToInteger(day);
        validate(this.day);
    }

    private void validate(int day) {
        if (day < START_DATE || day > END_DATE) {
            throw new InvalidDateException();
        }
    }

    private int convertToInteger(String quantity) {
        try {
            return Integer.parseInt(quantity);
        } catch (Exception e) {
            throw new InvalidDateException();
        }
    }

    public int getDay() {
        return day;
    }

    public Week getDayOfWeek() {
        LocalDate date = LocalDate.of(ORDER_YEAR, ORDER_MONTH, day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        return Week.find(dayOfWeek.getValue());
    }

}
