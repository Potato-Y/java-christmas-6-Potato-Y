package christmas.order.model;

import static christmas.order.model.OrderSheet.MAXIMUM_ORDER_QUANTITY;
import static christmas.order.model.OrderSheet.MINIMUM_ORDER_QUANTITY;

import christmas.exception.InvalidOrderException;

public class Quantity {


    private final int quantity;

    public Quantity(String inQuantity) {
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
