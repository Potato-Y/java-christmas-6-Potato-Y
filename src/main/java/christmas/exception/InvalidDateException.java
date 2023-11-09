package christmas.exception;

/**
 * 유효하지 않은 날짜에 대한 예외
 */
public class InvalidDateException extends BaseIllegalArgumentException {

    private static final String MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public InvalidDateException() {
        super(MESSAGE);
    }

}
