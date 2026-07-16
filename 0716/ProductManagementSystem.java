import java.util.Scanner;

public class ProductManagementSystem {
    private static final int MAX_PRODUCTS = 10;

    private static int addCount = 0;
    private static int sellCount = 0;
    private static int restockCount = 0;
    private static int priceChangeCount = 0;
    private static int searchCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Product[] products = new Product[MAX_PRODUCTS];
        int productCount = initializeProducts(products);

        int choice;

        do {
            displayMenu();
            choice = readInteger(scanner, "Please enter your choice: ");

            switch (choice) {
                case 1:
                    displayAllProducts(products, productCount);
                    break;

                case 2:
                    searchProduct(scanner, products, productCount);
                    break;

                case 3:
                    productCount = addProduct(
                            scanner, products, productCount);
                    break;

                case 4:
                    sellProduct(scanner, products, productCount);
                    break;

                case 5:
                    restockProduct(scanner, products, productCount);
                    break;

                case 6:
                    changeProductPrice(
                            scanner, products, productCount);
                    break;

                case 7:
                    displayLowStockProducts(products, productCount);
                    break;

                case 8:
                    displayTotalInventoryValue(products, productCount);
                    break;

                case 9:
                    displayOperationSummary();
                    System.out.println("System closed.");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter 1 to 9.");
            }

            System.out.println();
        } while (choice != 9);

        scanner.close();
    }

    public static int initializeProducts(Product[] products) {
        products[0] = new Product("Notebook", 50, 20);
        products[1] = new Product("Pen", 20, 30);
        products[2] = new Product("Eraser", 10, 8);
        products[3] = new Product("Ruler", 25, 5);
        products[4] = new Product("Pencil Case", 150, 12);

        return 5;
    }

    public static void displayMenu() {
        System.out.println("===== Product Management System =====");
        System.out.println("1. Display all products");
        System.out.println("2. Search product by name");
        System.out.println("3. Add product");
        System.out.println("4. Sell product");
        System.out.println("5. Restock product");
        System.out.println("6. Change product price");
        System.out.println("7. Display low-stock products");
        System.out.println("8. Display total inventory value");
        System.out.println("9. Exit and display operation summary");
    }

    public static void displayAllProducts(
            Product[] products, int productCount) {

        System.out.println("===== All Products =====");

        if (productCount == 0) {
            System.out.println("No products available.");
            return;
        }

        for (int i = 0; i < productCount; i++) {
            System.out.println((i + 1) + ". " + products[i]);
        }
    }

    public static void searchProduct(
            Scanner scanner, Product[] products, int productCount) {

        System.out.print("Enter the complete product name: ");
        String name = scanner.nextLine();

        int index = findProductIndex(products, productCount, name);
        searchCount++;

        if (index == -1) {
            System.out.println("Product not found.");
        } else {
            System.out.println("Product found:");
            System.out.println(products[index]);
        }
    }

    public static int addProduct(
            Scanner scanner, Product[] products, int productCount) {

        if (productCount >= products.length) {
            System.out.println("Product array is full. Cannot add product.");
            return productCount;
        }

        System.out.print("Enter product name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Product name cannot be empty.");
            return productCount;
        }

        if (findProductIndex(products, productCount, name) != -1) {
            System.out.println("A product with this name already exists.");
            return productCount;
        }

        int price = readInteger(scanner, "Enter product price: ");

        if (price <= 0) {
            System.out.println("Price must be greater than 0.");
            return productCount;
        }

        int stock = readInteger(scanner, "Enter initial stock: ");

        if (stock < 0) {
            System.out.println("Stock cannot be less than 0.");
            return productCount;
        }

        products[productCount] = new Product(name, price, stock);
        addCount++;

        System.out.println("Product added successfully.");
        System.out.println(products[productCount]);

        return productCount + 1;
    }

    public static void sellProduct(
            Scanner scanner, Product[] products, int productCount) {

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        int index = findProductIndex(products, productCount, name);

        if (index == -1) {
            System.out.println("Product not found.");
            return;
        }

        int quantity = readInteger(scanner, "Enter quantity to sell: ");
        boolean result = products[index].sell(quantity);

        if (result) {
            sellCount++;
            System.out.println("Product sold successfully.");
            System.out.println(products[index]);
        } else {
            System.out.println(
                    "Sale failed. Check the quantity and stock.");
        }
    }

    public static void restockProduct(
            Scanner scanner, Product[] products, int productCount) {

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        int index = findProductIndex(products, productCount, name);

        if (index == -1) {
            System.out.println("Product not found.");
            return;
        }

        int quantity = readInteger(scanner, "Enter restock quantity: ");
        boolean result = products[index].restock(quantity);

        if (result) {
            restockCount++;
            System.out.println("Product restocked successfully.");
            System.out.println(products[index]);
        } else {
            System.out.println(
                    "Restock failed. Quantity must be greater than 0.");
        }
    }

    public static void changeProductPrice(
            Scanner scanner, Product[] products, int productCount) {

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        int index = findProductIndex(products, productCount, name);

        if (index == -1) {
            System.out.println("Product not found.");
            return;
        }

        int newPrice = readInteger(scanner, "Enter new price: ");
        boolean result = products[index].setPrice(newPrice);

        if (result) {
            priceChangeCount++;
            System.out.println("Price changed successfully.");
            System.out.println(products[index]);
        } else {
            System.out.println(
                    "Price change failed. Price must be greater than 0.");
        }
    }

    public static void displayLowStockProducts(
            Product[] products, int productCount) {

        System.out.println("===== Low-Stock Products =====");

        boolean found = false;

        for (int i = 0; i < productCount; i++) {
            if (products[i].isLowStock()) {
                System.out.println(products[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No low-stock products.");
        }
    }

    public static void displayTotalInventoryValue(
            Product[] products, int productCount) {

        int totalValue = 0;

        for (int i = 0; i < productCount; i++) {
            totalValue += products[i].getInventoryValue();
        }

        System.out.println(
                "Total inventory value: $" + totalValue);
    }

    public static int findProductIndex(
            Product[] products, int productCount, String name) {

        if (name == null) {
            return -1;
        }

        String targetName = name.trim();

        for (int i = 0; i < productCount; i++) {
            if (products[i].getName()
                    .trim()
                    .equalsIgnoreCase(targetName)) {

                return i;
            }
        }

        return -1;
    }

    public static int readInteger(
            Scanner scanner, String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(
                        "Invalid input. Please enter an integer.");
            }
        }
    }

    public static void displayOperationSummary() {
        System.out.println("===== Operation Summary =====");
        System.out.println("Successful additions: " + addCount);
        System.out.println("Successful sales: " + sellCount);
        System.out.println("Successful restocks: " + restockCount);
        System.out.println(
                "Successful price changes: " + priceChangeCount);
        System.out.println("Product searches: " + searchCount);
    }
}
/*
Please enter your choice: 1
===== All Products =====
1. Product Name: Notebook, Price: $50, Stock: 20, Inventory Value: $1000
2. Product Name: Pen, Price: $20, Stock: 30, Inventory Value: $600
3. Product Name: Eraser, Price: $10, Stock: 8, Inventory Value: $80
4. Product Name: Ruler, Price: $25, Stock: 5, Inventory Value: $125
5. Product Name: Pencil Case, Price: $150, Stock: 12, Inventory Value: $1800

Please enter your choice: 2
Enter the complete product name: Notebook
Product found:
Product Name: Notebook, Price: $50, Stock: 20, Inventory Value: $1000Please enter your choice: 2
Enter the complete product name: Notebook
Product found:
Product Name: Notebook, Price: $50, Stock: 20, Inventory Value: $1000

Please enter your choice: 3
Enter product name: Notebook
A product with this name already exists.

Please enter your choice: 4
Enter product name: Ruler
Enter quantity to sell: 56
Sale failed. Check the quantity and stock.

Please enter your choice: 5
Enter product name: Pencil Case
Enter restock quantity: 45
Product restocked successfully.
Product Name: Pencil Case, Price: $150, Stock: 57, Inventory Value: $8550

Please enter your choice: 6
Enter product name: Pen
Enter new price: 25
Price changed successfully.
Product Name: Pen, Price: $25, Stock: 30, Inventory Value: $750

Please enter your choice: 7
===== Low-Stock Products =====
Product Name: Eraser, Price: $10, Stock: 8, Inventory Value: $80
Product Name: Ruler, Price: $25, Stock: 5, Inventory Value: $125

Please enter your choice: 8
Total inventory value: $10505

Please enter your choice: 9
===== Operation Summary =====
Successful additions: 0
Successful sales: 0
Successful restocks: 1
Successful price changes: 1
Product searches: 1
System closed.

 */