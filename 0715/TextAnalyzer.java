import java.util.Scanner;

public class TextAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = inputNonEmptyText(scanner, "請輸入一行非空白文字：");
        String trimmedText = text.trim();
        String[] words = splitWords(trimmedText);

        System.out.println("\n===== 文字分析結果 =====");
        System.out.println("原始字元數：" + text.length());
        System.out.println("有效字元數：" + trimmedText.length());
        System.out.println("單字數量：" + words.length);
        System.out.println("英文母音總數：" + countVowels(text));
        System.out.println("最長單字：" + findLongestWord(words));

        String keyword = inputNonEmptyText(scanner, "\n請輸入要搜尋的關鍵字：");
        int keywordCount = countKeyword(text, keyword);

        System.out.println("關鍵字「" + keyword + "」出現次數：" + keywordCount);

        scanner.close();
    }

    public static String inputNonEmptyText(Scanner scanner, String message) {
        String input;

        while (true) {
            System.out.print(message);
            input = scanner.nextLine();

            if (!input.trim().isEmpty()) {
                return input;
            }

            System.out.println("輸入不可為空字串或全空白，請重新輸入。");
        }
    }

    public static String[] splitWords(String text) {
        return text.split("\\s+");
    }

    public static int countVowels(String text) {
        int count = 0;
        String lowerText = text.toLowerCase();

        for (int i = 0; i < lowerText.length(); i++) {
            char character = lowerText.charAt(i);

            if (isVowel(character)) {
                count++;
            }
        }

        return count;
    }

    public static boolean isVowel(char character) {
        return character == 'a'
                || character == 'e'
                || character == 'i'
                || character == 'o'
                || character == 'u';
    }

    public static String findLongestWord(String[] words) {
        String longestWord = words[0];

        for (int i = 1; i < words.length; i++) {
            if (words[i].length() > longestWord.length()) {
                longestWord = words[i];
            }
        }

        return longestWord;
    }

    public static int countKeyword(String text, String keyword) {
        String lowerText = text.toLowerCase();
        String lowerKeyword = keyword.toLowerCase();

        int count = 0;
        int index = 0;

        while ((index = lowerText.indexOf(lowerKeyword, index)) != -1) {
            count++;
            index += lowerKeyword.length();
        }

        return count;
    }
}