package christmas;

import christmas.order.OrderController;
import christmas.order.model.OrderSheet;
import christmas.view.OutputView;

public class Running {

    OrderController orderController;

    public Running() {
        this.orderController = new OrderController();
    }

    public void run() {
        OrderSheet orderSheet = orderController.readOrder();
        OutputView.printOrderPrice(orderSheet.getOrderPrice());
    }

}
