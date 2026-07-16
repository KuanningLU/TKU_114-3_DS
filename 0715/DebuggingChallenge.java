/*
錯誤 1
錯誤位置：System.out.println("系統結束，年齡：" + age)
錯誤類型：編譯錯誤
原因：敘述結尾缺少分號。
修正方式：在敘述結尾加上分號。

錯誤 2
錯誤位置：for 迴圈條件 i <= scores.length
錯誤類型：陣列越界錯誤
原因：陣列最後一個索引是 scores.length - 1。
修正方式：將條件改為 i < scores.length。

錯誤 3
錯誤位置：command == "exit"
錯誤類型：字串比較錯誤
原因：== 比較的是物件位置，不是字串內容。
修正方式：使用 command.equalsIgnoreCase("exit")。

錯誤 4
錯誤位置：double average = total / scores.length;
錯誤類型：整數除法邏輯錯誤
原因：total 與 scores.length 都是整數，會先進行整數除法。
修正方式：將 total 轉換成 double 後再計算。

錯誤 5
錯誤位置：nextInt() 後直接使用 nextLine()
錯誤類型：Scanner 換行問題
原因：nextInt() 不會讀取使用者按下 Enter 所留下的換行字元。
修正方式：在 nextInt() 後增加一次 nextLine() 清除換行。

Breakpoint 紀錄
中斷點位置：for 迴圈執行 total += scores[i]; 的位置。

第一次執行前：
i = 0
scores[i] = 80
total = 0

第一次執行後：
i = 0
scores[i] = 80
total = 80

第二次執行後：
i = 1
scores[i] = 75
total = 155

第三次執行後：
i = 2
scores[i] = 92
total = 247
*/

import java.util.Scanner;

public class DebuggingChallenge {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] scores = {80, 75, 92};
        int total = 0;

        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        }

        double average = (double) total / scores.length;
        System.out.printf("平均：%.2f%n", average);

        System.out.print("請輸入年齡：");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("請輸入指令：");
        String command = sc.nextLine();

        if (command.equalsIgnoreCase("exit")) {
            System.out.println("系統結束，年齡：" + age);
        } else {
            System.out.println("指令錯誤，系統未結束。");
        }

        sc.close();
    }
}