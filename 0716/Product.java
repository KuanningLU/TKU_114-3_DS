public class Product {
    private String name;
    private int price;
    private int stock;

    public Product(String name, int price, int stock) {
        if (name == null || name.trim().isEmpty()) {
            this.name = "Unknown Product";
        } else {
            this.name = name;
        }

        if (price > 0) {
            this.price = price;
        } else {
            this.price = 1;
        }

        if (stock >= 0) {
            this.stock = stock;
        } else {
            this.stock = 0;
        }
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public boolean setPrice(int price) {
        if (price <= 0) {
            return false;
        }

        this.price = price;
        return true;
    }

    public boolean restock(int quantity) {
        if (quantity <= 0) {
            return false;
        }

        stock += quantity;
        return true;
    }

    public boolean sell(int quantity) {
        if (quantity <= 0 || quantity > stock) {
            return false;
        }

        stock -= quantity;
        return true;
    }

    public boolean isLowStock() {
        return stock < 10;
    }

    public int getInventoryValue() {
        return price * stock;
    }

    @Override
    public String toString() {
        return "Product Name: " + name
                + ", Price: $" + price
                + ", Stock: " + stock
                + ", Inventory Value: $" + getInventoryValue();
    }
}