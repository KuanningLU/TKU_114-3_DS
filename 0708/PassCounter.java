public class PassCounter {
    public static void main(String[] args) {
        int count = 0;

        if (80 >= 60) {
            count++;
        }

        if (55 >= 60) {
            count++;
        }

        if (70 >= 60) {
            count++;
        }

        System.out.println("Pass count: " + count);
    }
}