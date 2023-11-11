package christmas.view.util;

import java.text.DecimalFormat;

public class PrintUtil {

    public static void firstPrint(String text) {
        System.out.println();
        System.out.println(text);
    }

    public static void println(String text) {
        System.out.println(text);
    }

    public static void print(String text) {
        System.out.print(text);
    }

    public static void printlnWon(int money) {
        DecimalFormat format = new DecimalFormat("###,###원");

        String text = format.format(money);
        System.out.println(text);
    }

}
