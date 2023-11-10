package christmas.model;

import christmas.exception.InvalidOrderException;

public class OrderQuantity {

    private static final int MINIMUM_ORDER_QUANTITY = 1;
    public static final int MAXIMUM_ORDER_QUANTITY = 20;

    private final int quantity;

    public OrderQuantity(String inQuantity) {
        int quantity = convertToInteger(inQuantity);
        validate(quantity);

        this.quantity = quantity;
    }

    /**
     * 최소 주문 수량과 최대 주문 수량을 확인한다.
     *
     * @param quantity
     */
    private void validate(int quantity) {
        if (quantity < MINIMUM_ORDER_QUANTITY) {
            throw new InvalidOrderException();
        }
        if (quantity > MAXIMUM_ORDER_QUANTITY) {
            throw new InvalidOrderException();
        }
    }

    private int convertToInteger(String quantity) {
        try {
            return Integer.parseInt(quantity);
        } catch (Exception e) {
            throw new InvalidOrderException();
        }
    }

    public int getQuantity() {
        return quantity;
    }
}
