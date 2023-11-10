package christmas.util;

public enum Separator {

    COMMA(","),
    DASH("-");

    private final String separator;

    Separator(String separator) {
        this.separator = separator;
    }

    @Override
    public String toString() {
        return separator;
    }

}
