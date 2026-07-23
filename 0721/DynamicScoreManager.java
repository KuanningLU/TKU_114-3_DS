import java.util.ArrayList;
import java.util.Scanner;

public class DynamicScoreManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> scores = inputScores(scanner);

        if (scores.isEmpty()) {
            System.out.println("未輸入任何有效成績。");
        } else {
            printStatistics(scores);
            printPassingList(scores);
        }

        scanner.close();
    }

    public static ArrayList<Integer> inputScores(Scanner scanner) {
        ArrayList<Integer> scores = new ArrayList<>();

        System.out.println("請持續輸入成績（0～100），輸入 -1 結束：");

        while (true) {
            System.out.print("請輸入成績：");
            String input = scanner.nextLine();

            try {
                int score = Integer.parseInt(input);

                if (score == -1) {
                    break;
                }

                if (score >= 0 && score <= 100) {
                    scores.add(score);
                } else {
                    System.out.println("輸入錯誤，成績只能介於 0～100。");
                }
            } catch (NumberFormatException e) {
                System.out.println("輸入錯誤，請輸入整數。");
            }
        }

        return scores;
    }

    public static double calculateAverage(ArrayList<Integer> scores) {
        int total = 0;

        for (int score : scores) {
            total += score;
        }

        return (double) total / scores.size();
    }

    public static int findHighest(ArrayList<Integer> scores) {
        int highest = scores.get(0);

        for (int score : scores) {
            if (score > highest) {
                highest = score;
            }
        }

        return highest;
    }

    public static int findLowest(ArrayList<Integer> scores) {
        int lowest = scores.get(0);

        for (int score : scores) {
            if (score < lowest) {
                lowest = score;
            }
        }

        return lowest;
    }

    public static ArrayList<Integer> filterPassingScores(
            ArrayList<Integer> scores) {

        ArrayList<Integer> passingScores = new ArrayList<>();

        for (int score : scores) {
            if (score >= 60) {
                passingScores.add(score);
            }
        }

        return passingScores;
    }

    public static void printStatistics(ArrayList<Integer> scores) {
        System.out.println("\n===== 成績統計 =====");
        System.out.println("成績筆數：" + scores.size());
        System.out.printf("平均成績：%.2f%n", calculateAverage(scores));
        System.out.println("最高成績：" + findHighest(scores));
        System.out.println("最低成績：" + findLowest(scores));
    }

    public static void printPassingList(ArrayList<Integer> scores) {
        ArrayList<Integer> passingScores = filterPassingScores(scores);

        System.out.println("\n===== 及格名單 =====");

        if (passingScores.isEmpty()) {
            System.out.println("沒有及格成績。");
        } else {
            for (int i = 0; i < passingScores.size(); i++) {
                System.out.println(
                    "第 " + (i + 1) + " 位："
                    + passingScores.get(i) + " 分"
                );
            }
        }
    }
}