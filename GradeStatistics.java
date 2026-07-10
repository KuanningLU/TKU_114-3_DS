import java.util.Scanner;

public class GradeStatistics {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = 0;
        int total = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int passCount = 0;
        int failCount = 0;

        System.out.println("===== Grade Statistics System =====");

        while (true) {
            int score = readInteger(sc, "請輸入成績（輸入 -1 結束）：");

            if (score == -1) {
                break;
            }

            if (!isValidScore(score)) {
                System.out.println("輸入錯誤：成績必須介於 0 到 100，請重新輸入。");
                continue;
            }

            total += score;
            count++;

            if (score > max) {
                max = score;
            }

            if (score < min) {
                min = score;
            }

            // 計算及格與不及格人數
            if (isPassing(score)) {
                passCount++;
            } else {
                failCount++;
            }
        }

        if (count == 0) {
            System.out.println("No scores entered.");
        } else {
            double average = (double) total / count;
            String grade = getGrade(average);

            printSummary(
                    count,
                    total,
                    average,
                    max,
                    min,
                    passCount,
                    failCount,
                    grade);
        }

        sc.close();
    }

    public static int readInteger(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("輸入錯誤：請輸入整數。");
            }
        }
    }

    public static boolean isValidScore(int score) {
        return score >= 0 && score <= 100;
    }

    public static boolean isPassing(int score) {
        return score >= 60;
    }

    public static String getGrade(double average) {
        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void printSummary(
            int count,
            int total,
            double average,
            int max,
            int min,
            int passCount,
            int failCount,
            String grade) {

        System.out.println();
        System.out.println("=== Grade Summary ===");
        System.out.println("Count: " + count);
        System.out.println("Total: " + total);
        System.out.println("Average: " + average);
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
        System.out.println("Pass count: " + passCount);
        System.out.println("Fail count: " + failCount);
        System.out.println("Average grade: " + grade);
    }
}