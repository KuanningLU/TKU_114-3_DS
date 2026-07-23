import java.util.ArrayList;
import java.util.Scanner;

public class EquipmentManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Equipment> equipmentList = new ArrayList<>();

        int choice;

        do {
            showMenu();
            choice = readChoice(scanner);

            switch (choice) {
                case 1:
                    addEquipment(scanner, equipmentList);
                    break;

                case 2:
                    searchEquipment(scanner, equipmentList);
                    break;

                case 3:
                    borrowEquipment(scanner, equipmentList);
                    break;

                case 4:
                    returnEquipment(scanner, equipmentList);
                    break;

                case 5:
                    listAvailableEquipment(equipmentList);
                    break;

                case 6:
                    listAllEquipment(equipmentList);
                    break;

                case 0:
                    System.out.println("程式結束。");
                    break;

                default:
                    System.out.println("輸入錯誤，請輸入 0～6。");
            }

        } while (choice != 0);

        scanner.close();
    }

    public static void showMenu() {
        System.out.println("\n===== 設備物件集合管理系統 =====");
        System.out.println("1. 新增設備");
        System.out.println("2. 依代碼搜尋設備");
        System.out.println("3. 借出設備");
        System.out.println("4. 歸還設備");
        System.out.println("5. 列出可借設備");
        System.out.println("6. 列出全部設備");
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

    public static void addEquipment(
            Scanner scanner,
            ArrayList<Equipment> equipmentList) {

        System.out.print("請輸入設備代碼：");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("新增失敗，設備代碼不得為空白。");
            return;
        }

        if (findEquipmentByCode(equipmentList, code) != null) {
            System.out.println("新增失敗，設備代碼不可重複。");
            return;
        }

        System.out.print("請輸入設備名稱：");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("新增失敗，設備名稱不得為空白。");
            return;
        }

        Equipment equipment = new Equipment(code, name);
        equipmentList.add(equipment);

        System.out.println("設備新增成功。");
        System.out.println(equipment);
    }

    public static void searchEquipment(
            Scanner scanner,
            ArrayList<Equipment> equipmentList) {

        System.out.print("請輸入要搜尋的設備代碼：");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("搜尋失敗，設備代碼不得為空白。");
            return;
        }

        Equipment equipment = findEquipmentByCode(equipmentList, code);

        if (equipment == null) {
            System.out.println("找不到設備代碼：" + code);
        } else {
            System.out.println("找到設備：");
            System.out.println(equipment);
        }
    }

    public static void borrowEquipment(
            Scanner scanner,
            ArrayList<Equipment> equipmentList) {

        System.out.print("請輸入要借出的設備代碼：");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("借出失敗，設備代碼不得為空白。");
            return;
        }

        Equipment equipment = findEquipmentByCode(equipmentList, code);

        if (equipment == null) {
            System.out.println("借出失敗，找不到設備代碼：" + code);
            return;
        }

        if (!equipment.isAvailable()) {
            System.out.println("借出失敗，此設備目前已借出。");
            return;
        }

        equipment.borrowEquipment();
        System.out.println("設備借出成功：" + equipment.getName());
    }

    public static void returnEquipment(
            Scanner scanner,
            ArrayList<Equipment> equipmentList) {

        System.out.print("請輸入要歸還的設備代碼：");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("歸還失敗，設備代碼不得為空白。");
            return;
        }

        Equipment equipment = findEquipmentByCode(equipmentList, code);

        if (equipment == null) {
            System.out.println("歸還失敗，找不到設備代碼：" + code);
            return;
        }

        if (equipment.isAvailable()) {
            System.out.println("歸還失敗，此設備目前未借出。");
            return;
        }

        equipment.returnEquipment();
        System.out.println("設備歸還成功：" + equipment.getName());
    }

    public static void listAvailableEquipment(
            ArrayList<Equipment> equipmentList) {

        System.out.println("\n===== 可借設備 =====");

        boolean found = false;

        for (Equipment equipment : equipmentList) {
            if (equipment.isAvailable()) {
                System.out.println(equipment);
                found = true;
            }
        }

        if (!found) {
            System.out.println("目前沒有可借用的設備。");
        }
    }

    public static void listAllEquipment(
            ArrayList<Equipment> equipmentList) {

        System.out.println("\n===== 全部設備 =====");

        if (equipmentList.isEmpty()) {
            System.out.println("目前沒有任何設備資料。");
            return;
        }

        for (int i = 0; i < equipmentList.size(); i++) {
            System.out.println((i + 1) + ". " + equipmentList.get(i));
        }
    }

    public static Equipment findEquipmentByCode(
            ArrayList<Equipment> equipmentList,
            String targetCode) {

        for (Equipment equipment : equipmentList) {
            if (equipment.getCode().equalsIgnoreCase(targetCode)) {
                return equipment;
            }
        }

        return null;
    }
}