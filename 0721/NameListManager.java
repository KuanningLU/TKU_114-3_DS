import java.util.ArrayList;
import java.util.Scanner;

public class NameListManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> nameList = new ArrayList<>();

        int choice;

        do {
            showMenu();
            choice = readChoice(scanner);

            switch (choice) {
                case 1:
                    addName(scanner, nameList);
                    break;
                case 2:
                    searchName(scanner, nameList);
                    break;
                case 3:
                    updateName(scanner, nameList);
                    break;
                case 4:
                    deleteName(scanner, nameList);
                    break;
                case 5:
                    showAllNames(nameList);
                    break;
                case 0:
                    System.out.println("程式結束。");
                    break;
                default:
                    System.out.println("輸入錯誤，請輸入 0～5。");
            }

        } while (choice != 0);

        scanner.close();
    }

    public static void showMenu() {
        System.out.println("\n===== 名單管理系統 =====");
        System.out.println("1. 新增姓名");
        System.out.println("2. 搜尋姓名");
        System.out.println("3. 修改姓名");
        System.out.println("4. 刪除姓名");
        System.out.println("5. 列出全部姓名");
        System.out.println("0. 結束程式");
    }

    public static int readChoice(Scanner scanner) {
        while (true) {
            System.out.print("請輸入選項：");
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("輸入錯誤，請輸入整數。");
            }
        }
    }

    public static void addName(
            Scanner scanner, ArrayList<String> nameList) {

        System.out.print("請輸入要新增的姓名：");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("新增失敗，姓名不得為空白。");
            return;
        }

        nameList.add(name);
        System.out.println("新增成功：" + name);
    }

    public static void searchName(
            Scanner scanner, ArrayList<String> nameList) {

        System.out.print("請輸入要搜尋的姓名：");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("搜尋失敗，姓名不得為空白。");
            return;
        }

        int index = findNameIndex(nameList, name);

        if (index != -1) {
            System.out.println("找到姓名：" + nameList.get(index));
        } else {
            System.out.println("找不到姓名：" + name);
        }
    }

    public static void updateName(
            Scanner scanner, ArrayList<String> nameList) {

        System.out.print("請輸入要修改的原姓名：");
        String oldName = scanner.nextLine().trim();

        if (oldName.isEmpty()) {
            System.out.println("修改失敗，姓名不得為空白。");
            return;
        }

        int index = findNameIndex(nameList, oldName);

        if (index == -1) {
            System.out.println("修改失敗，找不到姓名：" + oldName);
            return;
        }

        System.out.print("請輸入新的姓名：");
        String newName = scanner.nextLine().trim();

        if (newName.isEmpty()) {
            System.out.println("修改失敗，新姓名不得為空白。");
            return;
        }

        nameList.set(index, newName);
        System.out.println("修改成功：" + oldName + " → " + newName);
    }

    public static void deleteName(
            Scanner scanner, ArrayList<String> nameList) {

        System.out.print("請輸入要刪除的姓名：");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("刪除失敗，姓名不得為空白。");
            return;
        }

        int index = findNameIndex(nameList, name);

        if (index != -1) {
            String deletedName = nameList.remove(index);
            System.out.println("刪除成功：" + deletedName);
        } else {
            System.out.println("刪除失敗，找不到姓名：" + name);
        }
    }

    public static void showAllNames(ArrayList<String> nameList) {
        System.out.println("\n===== 全部名單 =====");

        if (nameList.isEmpty()) {
            System.out.println("目前沒有任何姓名。");
            return;
        }

        for (int i = 0; i < nameList.size(); i++) {
            System.out.println((i + 1) + ". " + nameList.get(i));
        }
    }

    public static int findNameIndex(
            ArrayList<String> nameList, String targetName) {

        for (int i = 0; i < nameList.size(); i++) {
            if (nameList.get(i).equalsIgnoreCase(targetName)) {
                return i;
            }
        }

        return -1;
    }
}