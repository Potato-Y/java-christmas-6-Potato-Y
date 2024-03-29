package christmas.event;

import static christmas.view.InputView.reReadLine;

import christmas.event.dto.EventResultDto;
import christmas.event.model.DayOfMonth;
import christmas.order.model.OrderSheet;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventController {

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

    public EventResultDto calculateEvent(OrderSheet orderSheet, DayOfMonth dayOfMonth) {
        return EventResultFilter.eventResult(orderSheet, dayOfMonth);
    }

}
