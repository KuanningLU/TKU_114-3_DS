public class TemperatureLevel {
    public static void main(String[] args) {
        double temperature = 22;

        if (temperature < 15) {
            System.out.println("Cold");
        } else if (temperature < 28) {
            System.out.println("Comfortable");
        } else {
            System.out.println("Hot");
        }
    }
}