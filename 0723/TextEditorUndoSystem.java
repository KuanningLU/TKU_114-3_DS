import java.util.Scanner;
import java.util.Stack;

public class TextEditorUndoSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<String> history = new Stack<>();

        String text = "";
        boolean running = true;

        while (running) {
            System.out.println("\n===== 文字編輯 Undo 系統 =====");
            System.out.println("目前內容：" + text);
            System.out.println("1. 新增文字");
            System.out.println("2. 刪除最後數個字元");
            System.out.println("3. Undo");
            System.out.println("4. 顯示目前內容");
            System.out.println("0. 結束程式");
            System.out.print("請輸入選項：");

            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    System.out.print("請輸入要新增的文字：");
                    String newText = scanner.nextLine();

                    history.push(text);
                    text = text + newText;

                    System.out.println("新增完成。");
                    System.out.println("目前內容：" + text);
                    break;

                case "2":
                    if (text.isEmpty()) {
                        System.out.println("目前沒有文字可以刪除。");
                        break;
                    }

                    System.out.print("請輸入要刪除的字元數：");
                    String input = scanner.nextLine().trim();

                    try {
                        int count = Integer.parseInt(input);

                        if (count <= 0) {
                            System.out.println("刪除字元數必須大於 0。");
                        } else if (count > text.length()) {
                            System.out.println("刪除字元數不可超過目前文字長度。");
                        } else {
                            history.push(text);
                            text = text.substring(0, text.length() - count);

                            System.out.println("刪除完成。");
                            System.out.println("目前內容：" + text);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("輸入錯誤，請輸入整數。");
                    }
                    break;

                case "3":
                    if (history.isEmpty()) {
                        System.out.println("沒有可以 Undo 的歷史紀錄。");
                    } else {
                        text = history.pop();

                        System.out.println("Undo 完成。");
                        System.out.println("目前內容：" + text);
                    }
                    break;

                case "4":
                    System.out.println("目前內容：" + text);
                    break;

                case "0":
                    running = false;
                    System.out.println("程式結束。");
                    break;

                default:
                    System.out.println("輸入錯誤，請輸入 0～4。");
            }
        }

        scanner.close();
    }
}