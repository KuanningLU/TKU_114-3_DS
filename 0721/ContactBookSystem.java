import java.util.ArrayList;
import java.util.Scanner;

public class ContactBookSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Contact> contactList = new ArrayList<>();

        int choice;

        do {
            showMenu();
            choice = readChoice(scanner);

            switch (choice) {
                case 1:
                    addContact(scanner, contactList);
                    break;

                case 2:
                    searchContact(scanner, contactList);
                    break;

                case 3:
                    updatePhone(scanner, contactList);
                    break;

                case 4:
                    deleteContact(scanner, contactList);
                    break;

                case 5:
                    showAllContacts(contactList);
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
        System.out.println("\n===== 聯絡人管理系統 =====");
        System.out.println("1. 新增聯絡人");
        System.out.println("2. 搜尋聯絡人");
        System.out.println("3. 修改電話");
        System.out.println("4. 刪除聯絡人");
        System.out.println("5. 顯示完整清單");
        System.out.println("0. 結束程式");
    }

    public static int readChoice(Scanner scanner) {
        while (true) {
            System.out.print("請輸入選項：");
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("輸入錯誤，請輸入整數。");
            }
        }
    }

    public static void addContact(
            Scanner scanner,
            ArrayList<Contact> contactList) {

        System.out.print("請輸入聯絡人代碼：");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("新增失敗，代碼不得為空白。");
            return;
        }

        if (findContactByCode(contactList, code) != null) {
            System.out.println("新增失敗，聯絡人代碼不可重複。");
            return;
        }

        System.out.print("請輸入姓名：");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("新增失敗，姓名不得為空白。");
            return;
        }

        System.out.print("請輸入電話：");
        String phone = scanner.nextLine().trim();

        if (phone.isEmpty()) {
            System.out.println("新增失敗，電話不得為空白。");
            return;
        }

        System.out.print("請輸入電子郵件：");
        String email = scanner.nextLine().trim();

        if (email.isEmpty()) {
            System.out.println("新增失敗，電子郵件不得為空白。");
            return;
        }

        Contact contact = new Contact(code, name, phone, email);
        contactList.add(contact);

        System.out.println("聯絡人新增成功。");
        System.out.println(contact);
    }

    public static void searchContact(
            Scanner scanner,
            ArrayList<Contact> contactList) {

        System.out.print("請輸入要搜尋的聯絡人代碼：");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("搜尋失敗，代碼不得為空白。");
            return;
        }

        Contact contact = findContactByCode(contactList, code);

        if (contact == null) {
            System.out.println("找不到聯絡人代碼：" + code);
        } else {
            System.out.println("找到聯絡人：");
            System.out.println(contact);
        }
    }

    public static void updatePhone(
            Scanner scanner,
            ArrayList<Contact> contactList) {

        System.out.print("請輸入要修改電話的聯絡人代碼：");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("修改失敗，代碼不得為空白。");
            return;
        }

        Contact contact = findContactByCode(contactList, code);

        if (contact == null) {
            System.out.println("修改失敗，找不到聯絡人代碼：" + code);
            return;
        }

        System.out.println("目前電話：" + contact.getPhone());
        System.out.print("請輸入新的電話：");
        String newPhone = scanner.nextLine().trim();

        if (newPhone.isEmpty()) {
            System.out.println("修改失敗，新電話不得為空白。");
            return;
        }

        contact.setPhone(newPhone);
        System.out.println("電話修改成功。");
        System.out.println(contact);
    }

    public static void deleteContact(
            Scanner scanner,
            ArrayList<Contact> contactList) {

        System.out.print("請輸入要刪除的聯絡人代碼：");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("刪除失敗，代碼不得為空白。");
            return;
        }

        Contact contact = findContactByCode(contactList, code);

        if (contact == null) {
            System.out.println("刪除失敗，找不到聯絡人代碼：" + code);
            return;
        }

        contactList.remove(contact);
        System.out.println("聯絡人刪除成功：" + contact.getName());
    }

    public static void showAllContacts(
            ArrayList<Contact> contactList) {

        System.out.println("\n===== 完整聯絡人清單 =====");

        if (contactList.isEmpty()) {
            System.out.println("目前沒有任何聯絡人資料。");
            return;
        }

        for (int i = 0; i < contactList.size(); i++) {
            System.out.println((i + 1) + ". " + contactList.get(i));
        }
    }

    public static Contact findContactByCode(
            ArrayList<Contact> contactList,
            String targetCode) {

        for (Contact contact : contactList) {
            if (contact.getCode().equalsIgnoreCase(targetCode)) {
                return contact;
            }
        }

        return null;
    }
}