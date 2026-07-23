public class CartItem {
    private String code;
    private String name;
    private double unitPrice;
    private int quantity;

    public CartItem(String code, String name, double unitPrice, int quantity) {
        this.code = code;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int amount) {
        quantity += amount;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double calculateSubtotal() {
        return unitPrice * quantity;
    }

    @Override
    public String toString() {
        return String.format(
                "代碼：%s，名稱：%s，單價：%.2f，數量：%d，小計：%.2f",
                code, name, unitPrice, quantity, calculateSubtotal()
        );
    }
}