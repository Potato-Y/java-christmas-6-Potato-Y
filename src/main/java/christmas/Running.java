package christmas;

import christmas.event.EventController;
import christmas.event.dto.EventResultDto;
import christmas.event.model.DayOfMonth;
import christmas.order.OrderController;
import christmas.order.model.OrderSheet;
import christmas.view.OutputView;

public class Running {

    OrderController orderController;
    EventController eventController;

    public Running() {
        this.orderController = new OrderController();
        this.eventController = new EventController();
    }

    public void run() {
        DayOfMonth dayOfMonth = eventController.readDayOfMonth();
        OrderSheet orderSheet = orderController.readOrder();
        OutputView.printOrderPrice(orderSheet.getOrderPrice());

        EventResultDto dto = eventController.calculateEvent(orderSheet, dayOfMonth);

        OutputView.printDiscountResult(orderSheet, dto);
    }

}
