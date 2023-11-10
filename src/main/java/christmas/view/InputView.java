package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.util.PrintUtil;

public class InputView {

    public static String readOrder() {
        PrintUtil.firstPrint("주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        return Console.readLine();
    }
}
