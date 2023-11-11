package christmas;

import christmas.event.EventController;
import christmas.event.model.DayOfMonth;
import christmas.order.OrderController;
import christmas.order.model.OrderSheet;
import christmas.view.OutputView;

public class Running {

    OrderController orderController;
    EventController dayController;

    public Running() {
        this.orderController = new OrderController();
        this.dayController = new EventController();
    }

    public void run() {
        DayOfMonth dayOfMonth = dayController.readDayOfMonth();
        OrderSheet orderSheet = orderController.readOrder();
        OutputView.printOrderPrice(orderSheet.getOrderPrice());
    }

}
