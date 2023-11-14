package christmas.view.util;

import java.text.DecimalFormat;

public class PrintUtil {

    public static void firstPrint(String text) {
        System.out.println();
        System.out.println(text);
    }

    /**
     * println() and println(<내용>)
     *
     * @param text
     */
    public static void titlePrint(String text) {
        String message = "<%s>";

        System.out.println();
        System.out.printf((message) + "%n", text);
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

    public static void printlnMinusWon(int money) {
        System.out.print("-");
        printlnWon(money);
    }

    /**
     * println("이름 n개")
     *
     * @param name
     * @param number
     */
    public static void printlnItemNumber(String name, int number) {
        String message = "%s %d개\n";

        System.out.printf((message), name, number);
    }

}
