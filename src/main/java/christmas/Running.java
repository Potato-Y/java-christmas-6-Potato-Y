package christmas;

import static christmas.view.OutputView.printPreviewGuide;

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
        printPreviewGuide();
        OutputView.printOrderSheet(orderSheet);
        OutputView.printOrderPrice(orderSheet.getOrderPrice());

        EventResultDto dto = eventController.calculateEvent(orderSheet, dayOfMonth);

        OutputView.printDiscountResult(orderSheet, dto);

        // TODO: DTO의 데이터를 통해 DB에 배지 저장하여 추후 활용
    }

}
