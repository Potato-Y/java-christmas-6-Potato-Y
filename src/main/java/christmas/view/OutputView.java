package christmas.view;

import christmas.view.util.PrintUtil;

public class OutputView {

    public static void printException(Exception e) {
        PrintUtil.println(e.getMessage());
    }

    public static void printOrderPrice(int orderPrice) {
        PrintUtil.firstPrint("<할인 전 총주문 금액>");
        PrintUtil.printlnWon(orderPrice);
    }

}
