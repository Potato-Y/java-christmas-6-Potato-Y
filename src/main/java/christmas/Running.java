package christmas;

import christmas.order.OrderController;
import christmas.order.model.OrderSheet;

public class Running {

    OrderController orderController;

    public Running() {
        this.orderController = new OrderController();
    }

    public void run() {
        OrderSheet orderSheet = orderController.readOrder();
    }
}
