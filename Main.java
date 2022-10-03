import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String floatToString(double value) {
        String amount = String.format("%.2f", value);
        return amount;
    }

    public static String salesTaxCalculation(double billAmount) {
        double taxAmount = 0.0;
        if (billAmount <= 1000) {
            taxAmount = billAmount * 0.125;
        } else if (billAmount <= 2500) {
            taxAmount = billAmount * 0.10;
        } else {
            taxAmount = billAmount * 0.075;
        }
        String tax = floatToString(taxAmount);
        return tax;
    }

    public static void main(String[] args) {
        System.out.println("Enter the name of customer:");
        Scanner sc = new Scanner(System.in);
        String custName = sc.nextLine();
        System.out.println("Enter the number of items customer purchases:");
        int numItemPurchase = Integer.parseInt(sc.nextLine());

        if (numItemPurchase <= 0) {
            System.out.println("Invalid Output");
            System.exit(-1);
        } else {
            // get item name,qty purchased in comma format
            List<OrderedItem> ordItemList = new ArrayList<>();
            for (int i = 0; i < numItemPurchase; i++) {
                System.out.println(
                        "Enter name and quantity (comma separate format) of purchased item no " + (i + 1) + ": ");
                String ordDetails = sc.nextLine();
                String ordInfo[] = ordDetails.split(",");
                String itemName = ordInfo[0];
                String itemQty = ordInfo[1];
                if (ItemData.isAvailable(itemName)) {

                    SnackItem s = ItemData.getItem(itemName);
                    String rate = s.getRate();
                    String discountRate = s.getDiscountRate();
                    String discountQty = s.getDiscountQty();
                    Double discountAmount = 0.0;
                    Double payAmount = Double.parseDouble(rate) * Double.parseDouble(itemQty);

                    if (Double.parseDouble(itemQty) >= Double.parseDouble(discountQty)) {

                        discountAmount = payAmount * (Double.parseDouble(discountRate) / 100);
                        payAmount = payAmount - discountAmount;
                    }

                    OrderedItem ordItem = new OrderedItem(itemName, rate, itemQty, floatToString(discountAmount),
                            floatToString(payAmount));
                    ordItemList.add(ordItem);

                } else {
                    // ordItem[i] = new OrderedItem(itemName, "0", itemQty, "0",
                    // "0");
                    System.out.println("!!! " + itemName.toUpperCase() + " is not available !!!");
                }

            }
            String datePattern = "dd-MM-yyyy";
            String dateInString = new SimpleDateFormat(datePattern).format(new Date());
            System.out.println("\nCustomer Bill");
            System.out.print("Customer Name: " + custName + "\t\t\t\t Date: " + dateInString + "\n");
            System.out.println(String.format("%-15s %-10s %-10s %-10s %-10s %-10s", "ITEM", "RATE", "QUANTITY",
                    "PRICE", "DISCOUNT", "AMOUNT"));
            // in for loop print ordered item info
            Double billAmount = 0.0;
            for (OrderedItem i : ordItemList) {
                String name = i.getItemName();
                String rate = i.getRate();
                String qty = i.getOrderQty();
                SnackItem s = ItemData.getItem(name);
                Double price = Double.parseDouble(s.getRate()) * Double.parseDouble(qty);
                String dis = i.getDiscountAmount();
                String amount = i.getPayAmount();
                billAmount += Double.parseDouble(amount);
                String output1 = String.format("%-15s %-10s %-10s %-10s %-10s %-10s", name, rate, qty, price, dis,
                        amount);
                System.out.println(output1);
            }
            String tax = salesTaxCalculation(billAmount);
            Double total = billAmount + Double.parseDouble(tax);

            System.out.print("\n\t\t\t\tBill Amount: " + billAmount + "\n");
            System.out.print("\t\t\t\tSales Tax Amount: " + tax + "\n");
            System.out.print("\t\t\t\tFinal Amount: " + total + "\n");
        }
        sc.close();
    }
}
