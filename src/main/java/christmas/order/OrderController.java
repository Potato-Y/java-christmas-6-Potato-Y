package christmas.order;

import christmas.order.model.OrderSheet;
import christmas.view.InputView;
import christmas.view.OutputView;

public class OrderController {

    public OrderSheet readOrder() {
        while (true) {
            try {
                String read = InputView.readOrder();
                OrderSheet orderSheet = new OrderSheet(read);
                
                return orderSheet;
            } catch (IllegalArgumentException e) {
                OutputView.printException(e);
            }
        }
    }

}
