import java.util.Scanner;

public class PersonalProfileApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== Personal Health System =====");

        String name = readName(sc, "請輸入姓名：");
        int age = readPositiveInt(sc, "請輸入年齡：");
        double height = readPositiveDouble(sc, "請輸入身高（公尺）：");
        double weight = readPositiveDouble(sc, "請輸入體重（公斤）：");

        // 計算 BMI 並取得相關判斷結果
        double bmi = calculateBmi(height, weight);
        String level = getBmiLevel(bmi);
        boolean adult = isAdult(age);

        printReport(name, age, adult, height, weight, bmi, level);

        sc.close();
    }

    public static String readName(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String name = sc.nextLine().trim();

            if (!name.isEmpty()) {
                return name;
            }

            System.out.println("輸入錯誤：姓名不可以是空白，請重新輸入。");
        }
    }

    public static int readPositiveInt(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            try {
                int value = Integer.parseInt(input);

                if (value > 0) {
                    return value;
                }

                System.out.println("輸入錯誤：數值必須大於 0，請重新輸入。");
            } catch (NumberFormatException e) {
                System.out.println("輸入錯誤：請輸入正確的整數。");
            }
        }
    }

    public static double readPositiveDouble(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            try {
                double value = Double.parseDouble(input);

                // 除了必須大於 0，也排除 Infinity 等不正常數值
                if (value > 0 && Double.isFinite(value)) {
                    return value;
                }

                System.out.println("輸入錯誤：數值必須大於 0，請重新輸入。");
            } catch (NumberFormatException e) {
                System.out.println("輸入錯誤：請輸入正確的數字。");
            }
        }
    }

    public static double calculateBmi(double height, double weight) {
        return weight / (height * height);
    }


    public static String getBmiLevel(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 24) {
            return "Normal";
        } else if (bmi < 27) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }


    public static boolean isAdult(int age) {
        return age >= 18;
    }

    public static void printReport(
            String name,
            int age,
            boolean adult,
            double height,
            double weight,
            double bmi,
            String level) {

        System.out.println();
        System.out.println("=== Personal Health Report ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Adult: " + adult);
        System.out.printf("Height: %.2f%n", height);
        System.out.printf("Weight: %.1f%n", weight);
        System.out.printf("BMI: %.2f%n", bmi);
        System.out.println("Level: " + level);
    }
}