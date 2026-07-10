public class PricePrinter {

    public static void main(String[] args) {
        printItem("Apple", 30);
        printItem("Milk", 50);
    }

    public static void printItem(String itemName, int price) {
        System.out.println("Item: " + itemName);
        System.out.println("Price: " + price);
    }
}