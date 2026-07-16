public class Rectangle {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        setWidth(width);
        setHeight(height);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double width) {
        if (width > 0) {
            this.width = width;
        } else {
            this.width = 1;
        }
    }

    public void setHeight(double height) {
        if (height > 0) {
            this.height = height;
        } else {
            this.height = 1;
        }
    }

    public double calculateArea() {
        return width * height;
    }

    public double calculatePerimeter() {
        return (width + height) * 2;
    }

    public boolean isSquare() {
        return width == height;
    }

    @Override
    public String toString() {
        return "Width: " + width + ", Height: " + height;
    }
}