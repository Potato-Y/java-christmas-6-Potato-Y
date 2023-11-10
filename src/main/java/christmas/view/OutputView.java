package christmas.view;

import christmas.view.util.PrintUtil;

public class OutputView {

    public static void printException(Exception e) {
        PrintUtil.println(e.getMessage());
    }

}
