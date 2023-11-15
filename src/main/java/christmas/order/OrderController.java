package christmas.order;

import static christmas.view.InputView.reReadLine;

import christmas.order.model.OrderSheet;
import christmas.view.InputView;
import christmas.view.OutputView;

public class OrderController {

    public OrderSheet readOrder() {
        String read = InputView.readOrder();
        while (true) {
            try {
                OrderSheet orderSheet = new OrderSheet(read);

                return orderSheet;
            } catch (IllegalArgumentException e) {
                OutputView.printException(e);
                read = reReadLine();
            }
        }
    }

}
