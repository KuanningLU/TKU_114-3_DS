import java.util.Scanner;
import java.util.Stack;

public class BracketValidationSystem {

    public static boolean isMatchingPair(char left, char right) {
        return (left == '(' && right == ')')
                || (left == '[' && right == ']')
                || (left == '{' && right == '}');
    }

    public static boolean isLeftBracket(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }

    public static boolean isRightBracket(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }

    public static boolean validateBrackets(String text) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (isLeftBracket(ch)) {
                stack.push(ch);
            } else if (isRightBracket(ch)) {
                if (stack.isEmpty()) {
                    System.out.println("錯誤：第 " + (i + 1)
                            + " 個字元出現多餘的右括號 " + ch);
                    return false;
                }

                char leftBracket = stack.pop();

                if (!isMatchingPair(leftBracket, ch)) {
                    System.out.println("錯誤：括號順序或種類不正確，"
                            + leftBracket + " 無法與 " + ch + " 配對。");
                    return false;
                }
            }
        }

        if (!stack.isEmpty()) {
            System.out.println("錯誤：缺少右括號，尚未配對的左括號為：" + stack);
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== 括號驗證系統 =====");
        System.out.print("請輸入要檢查的文字：");
        String text = scanner.nextLine();

        boolean result = validateBrackets(text);

        if (result) {
            System.out.println("驗證結果：括號配對正確。");
        } else {
            System.out.println("驗證結果：括號配對錯誤。");
        }

        scanner.close();
    }
}