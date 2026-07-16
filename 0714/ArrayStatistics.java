import java.util.Scanner;

public class ArrayStatistics {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = readCount(sc);
        int[] scores = new int[count];

        inputScores(sc, scores);

        System.out.println("\n===== 成績統計結果 =====");
        System.out.print("所有成績：");

        for (int score : scores) {
            System.out.print(score + " ");
        }

        System.out.println();

        int total = calculateTotal(scores);
        double average = (double) total / scores.length;
        int max = findMax(scores);
        int min = findMin(scores);
        int passCount = countPass(scores);
        double passRate = (double) passCount / scores.length * 100;

        System.out.println("總分：" + total);
        System.out.printf("平均：%.2f%n", average);
        System.out.println("最高分：" + max);
        System.out.println("最低分：" + min);
        System.out.println("及格人數：" + passCount);
        System.out.printf("及格率：%.2f%%%n", passRate);

        int target = readScore(sc, "\n請輸入要搜尋的目標成績（0～100）：");
        int index = findIndex(scores, target);

        if (index == -1) {
            System.out.println("找不到成績 " + target + "。");
        } else {
            System.out.println("成績 " + target + " 第一次出現在索引 " + index + "。");
        }

        sc.close();
    }

    public static int readCount(Scanner sc) {
        while (true) {
            System.out.print("請輸入成績筆數（1～50）：");

            if (sc.hasNextInt()) {
                int count = sc.nextInt();

                if (count >= 1 && count <= 50) {
                    return count;
                }

                System.out.println("輸入錯誤，筆數必須介於 1～50。");
            } else {
                System.out.println("輸入錯誤，請輸入整數。");
                sc.next();
            }
        }
    }

    public static void inputScores(Scanner sc, int[] scores) {
        for (int i = 0; i < scores.length; i++) {
            scores[i] = readScore(
                sc,
                "請輸入第 " + (i + 1) + " 筆成績（0～100）："
            );
        }
    }

    public static int readScore(Scanner sc, String message) {
        while (true) {
            System.out.print(message);

            if (sc.hasNextInt()) {
                int score = sc.nextInt();

                if (score >= 0 && score <= 100) {
                    return score;
                }

                System.out.println("輸入錯誤，成績必須介於 0～100。");
            } else {
                System.out.println("輸入錯誤，請輸入整數。");
                sc.next();
            }
        }
    }

    public static int calculateTotal(int[] scores) {
        int total = 0;

        for (int score : scores) {
            total += score;
        }

        return total;
    }

    public static int findMax(int[] scores) {
        int max = scores[0];

        for (int score : scores) {
            if (score > max) {
                max = score;
            }
        }

        return max;
    }

    public static int findMin(int[] scores) {
        int min = scores[0];

        for (int score : scores) {
            if (score < min) {
                min = score;
            }
        }

        return min;
    }

    public static int countPass(int[] scores) {
        int count = 0;

        for (int score : scores) {
            if (score >= 60) {
                count++;
            }
        }

        return count;
    }

    public static int findIndex(int[] scores, int target) {
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == target) {
                return i;
            }
        }

        return -1;
    }
}