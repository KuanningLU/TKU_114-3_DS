public class RectangleDemo {
    public static void main(String[] args) {
        Rectangle rectangle1 = new Rectangle(5, 3);
        Rectangle rectangle2 = new Rectangle(4, 4);
        Rectangle rectangle3 = new Rectangle(8, 2);

        displayRectangle("Rectangle 1", rectangle1);
        displayRectangle("Rectangle 2", rectangle2);
        displayRectangle("Rectangle 3", rectangle3);
    }

    public static void displayRectangle(String name, Rectangle rectangle) {
        System.out.println("===== " + name + " =====");
        System.out.println(rectangle);
        System.out.println("Area: " + rectangle.calculateArea());
        System.out.println("Perimeter: " + rectangle.calculatePerimeter());
        System.out.println("Is square: " + rectangle.isSquare());
        System.out.println();
    }
}