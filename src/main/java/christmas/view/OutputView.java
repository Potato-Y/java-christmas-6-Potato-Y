package christmas.view;

import christmas.badge.model.EventBadge;
import christmas.event.dto.EventResultDto;
import christmas.event.model.DiscountEvent;
import christmas.menu.model.RestaurantMenu;
import christmas.order.model.OrderSheet;
import christmas.order.model.Quantity;
import christmas.view.util.PrintUtil;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    private static final String NONE = "없음";

    public static void printException(Exception e) {
        PrintUtil.println(e.getMessage());
    }

    public static void printPreviewGuide(int day) {
        String message = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
        PrintUtil.println(String.format(message, day));
    }

    public static void printOrderSheet(OrderSheet orderSheet) {
        PrintUtil.titlePrint("주문 메뉴");

        for (Entry<RestaurantMenu, Quantity> item :
                orderSheet.getOrders().entrySet()) {
            PrintUtil.printlnItemNumber(item.getKey().getName(), item.getValue().getQuantity());
        }
    }

    public static void printOrderPrice(int orderPrice) {
        PrintUtil.titlePrint("할인 전 총주문 금액");
        PrintUtil.printlnWon(orderPrice);
    }

    public static void printDiscountResult(OrderSheet orderSheet, EventResultDto dto) {
        printGiveaway(dto.getGiveawayMenu());
        printDiscount(dto.getDiscount(), dto.getGiveawayMenu());
        printTotalBenefits(dto.getTotalGiveawayMenuAmount(), dto.getTotalDiscountAmount());
        printEstimatedPrice(orderSheet, dto.getTotalDiscountAmount());
        printBadge(dto.getEventBadge());
    }

    private static void printGiveaway(Map<RestaurantMenu, Quantity> giveaway) {
        PrintUtil.titlePrint("증정 메뉴");

        if (giveaway.isEmpty()) {
            PrintUtil.println(NONE);
            return;
        }

        for (Entry<RestaurantMenu, Quantity> item :
                giveaway.entrySet()) {
            PrintUtil.printlnItemNumber(item.getKey().getName(), item.getValue().getQuantity());
        }
    }

    public static void printGiveawayDiscount(Map<RestaurantMenu, Quantity> giveaway) {
        if (giveaway.isEmpty()) {
            return;
        }

        int discount = 0;
        for (Entry<RestaurantMenu, Quantity> item :
                giveaway.entrySet()) {
            discount += (item.getKey().getPrice() * item.getValue().getQuantity());
        }

        PrintUtil.print("증정 이벤트: ");
        PrintUtil.printlnMinusWon(discount);
    }

    private static void printDiscount(Map<DiscountEvent, Integer> discount, Map<RestaurantMenu, Quantity> giveaway) {
        PrintUtil.titlePrint("혜택 내역");

        if (discount.isEmpty() && giveaway.isEmpty()) {
            PrintUtil.println(NONE);
            return;
        }

        printGiveawayDiscount(giveaway);
        for (Entry<DiscountEvent, Integer> item :
                discount.entrySet()) {
            PrintUtil.print(item.getKey().getName() + ": ");
            PrintUtil.printlnMinusWon(item.getValue());
        }
    }

    private static void printTotalBenefits(int giveawayAmount, int discount) {
        PrintUtil.titlePrint("총혜택 금액");

        int totalDiscount = giveawayAmount + discount;
        if (totalDiscount == 0) {
            PrintUtil.printlnWon(totalDiscount);
            return;
        }

        PrintUtil.printlnMinusWon(totalDiscount);
    }

    private static void printEstimatedPrice(OrderSheet orderSheet, int totalDiscount) {
        PrintUtil.titlePrint("할인 후 예상 결제 금액");

        int price = orderSheet.getOrderPrice() - totalDiscount;
        if (price <= 0) {
            PrintUtil.printlnWon(0);
        }

        PrintUtil.printlnWon(price);
    }

    private static void printBadge(EventBadge eventBadge) {
        PrintUtil.titlePrint("12월 이벤트 배지");

        PrintUtil.println(eventBadge.getName());
    }

}
