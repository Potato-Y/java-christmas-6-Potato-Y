package christmas.event;

import static christmas.view.InputView.reReadLine;

import christmas.event.dto.EventResultDto;
import christmas.event.model.DayOfMonth;
import christmas.order.model.OrderSheet;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventController {

    private final EventCalculatorService calculatorService;

    public EventController() {
        this.calculatorService = new EventCalculatorService();
    }

    public DayOfMonth readDayOfMonth() {
        String input = InputView.readDayOfMonth();

        while (true) {
            try {
                DayOfMonth dayOfMonth = new DayOfMonth(input);
                return dayOfMonth;
            } catch (IllegalArgumentException e) {
                OutputView.printException(e);
                input = reReadLine();
            }
        }
    }

    public EventResultDto calculateEvent(OrderSheet orderSheet, DayOfMonth day) {
        return new EventCalculatorService().calculateEvent(orderSheet, day);
    }
}
