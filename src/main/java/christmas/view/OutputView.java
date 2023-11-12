package christmas.view;

import christmas.badge.model.EventBadge;
import christmas.event.dto.EventResultDto;
import christmas.event.model.DiscountEvent;
import christmas.menu.model.RestaurantMenu;
import christmas.order.model.OrderSheet;
import christmas.order.model.Quantity;
import christmas.view.util.BenefitCalculation;
import christmas.view.util.PrintUtil;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    private static final String NONE = "없음";

    public static void printException(Exception e) {
        PrintUtil.println(e.getMessage());
    }

    public static void printOrderPrice(int orderPrice) {
        PrintUtil.firstPrint("<할인 전 총주문 금액>");
        PrintUtil.printlnWon(orderPrice);
    }

    public static void printDiscountResult(OrderSheet orderSheet, EventResultDto dto) {
        printGiveaway(dto.getGiveawayMenu());
        printDiscount(dto.getDiscount(), dto.getGiveawayMenu());
        printTotalBenefits(dto.getGiveawayMenu(), dto.getDiscount());
        printEstimatedPrice(orderSheet, dto.getDiscount());
        printBadge(dto.getEventBadge());
    }

    public static void printGiveaway(Map<RestaurantMenu, Quantity> giveaway) {
        PrintUtil.firstPrint("<증정 메뉴>");

        if (giveaway.isEmpty()) {
            PrintUtil.println(NONE);
            return;
        }

        for (Entry<RestaurantMenu, Quantity> item :
                giveaway.entrySet()) {
            String message = String.format("%s %d개", item.getKey().getName(), item.getValue().getQuantity());
            PrintUtil.println(message);
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

        PrintUtil.print("증정 이벤트: -");
        PrintUtil.printlnWon(discount);
    }

    public static void printDiscount(Map<DiscountEvent, Integer> discount, Map<RestaurantMenu, Quantity> giveaway) {
        PrintUtil.firstPrint("<혜택 내역>");

        if (discount.isEmpty() && giveaway.isEmpty()) {
            PrintUtil.println(NONE);
            return;
        }

        printGiveawayDiscount(giveaway);
        for (Entry<DiscountEvent, Integer> item :
                discount.entrySet()) {
            PrintUtil.print(item.getKey().getName() + ": -");
            PrintUtil.printlnWon(item.getValue());
        }
    }

    public static void printTotalBenefits(Map<RestaurantMenu, Quantity> giveawayMenu,
                                          Map<DiscountEvent, Integer> discount) {
        PrintUtil.firstPrint("<총혜택 금액>");

        int totalDiscount = BenefitCalculation.calculateTotalBenefits(giveawayMenu, discount);
        if (totalDiscount == 0) {
            PrintUtil.printlnWon(totalDiscount);
            return;
        }

        PrintUtil.print("-");
        PrintUtil.printlnWon(totalDiscount);
    }

    public static void printEstimatedPrice(OrderSheet orderSheet, Map<DiscountEvent, Integer> discounts) {
        PrintUtil.firstPrint("<할인 후 예상 결제 금액>");

        int price = orderSheet.getOrderPrice() - BenefitCalculation.calculateDiscount(discounts);
        if (price <= 0) {
            PrintUtil.printlnWon(0);
        }

        PrintUtil.printlnWon(price);
    }

    private static void printBadge(EventBadge eventBadge) {
        PrintUtil.firstPrint("<12월 이벤트 배지>");

        PrintUtil.println(eventBadge.getName());
    }

}
