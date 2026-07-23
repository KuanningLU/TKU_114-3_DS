import java.util.ArrayList;
import java.util.Scanner;

public class CourseRegistrationSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Course> courseList = new ArrayList<>();

        int choice;

        do {
            showMenu();
            choice = readInteger(scanner, "請輸入選項：");

            switch (choice) {
                case 1:
                    addCourse(scanner, courseList);
                    break;

                case 2:
                    registerCourse(scanner, courseList);
                    break;

                case 3:
                    withdrawCourse(scanner, courseList);
                    break;

                case 4:
                    deleteCourse(scanner, courseList);
                    break;

                case 5:
                    searchCourse(scanner, courseList);
                    break;

                case 6:
                    showAllCourses(courseList);
                    break;

                case 7:
                    showStatistics(courseList);
                    break;

                case 0:
                    System.out.println("選課管理系統已結束。");
                    break;

                default:
                    System.out.println("輸入錯誤，請輸入 0～7。");
            }

        } while (choice != 0);

        scanner.close();
    }

    public static void showMenu() {
        System.out.println("\n===== 選課管理系統 =====");
        System.out.println("1. 新增課程");
        System.out.println("2. 選課");
        System.out.println("3. 退選");
        System.out.println("4. 刪除課程");
        System.out.println("5. 搜尋課程");
        System.out.println("6. 顯示全部課程");
        System.out.println("7. 顯示課程統計");
        System.out.println("0. 結束程式");
    }

    public static void addCourse(
            Scanner scanner,
            ArrayList<Course> courseList) {

        System.out.print("請輸入課程代碼：");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("新增失敗，課程代碼不得為空白。");
            return;
        }

        if (findCourseByCode(courseList, code) != null) {
            System.out.println("新增失敗，課程代碼不可重複。");
            return;
        }

        System.out.print("請輸入課程名稱：");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("新增失敗，課程名稱不得為空白。");
            return;
        }

        int capacity = readInteger(scanner, "請輸入課程容量：");

        if (capacity <= 0) {
            System.out.println("新增失敗，課程容量必須大於 0。");
            return;
        }

        Course course = new Course(code, name, capacity);
        courseList.add(course);

        System.out.println("課程新增成功。");
        System.out.println(course);
    }

    public static void registerCourse(
            Scanner scanner,
            ArrayList<Course> courseList) {

        System.out.print("請輸入要選課的課程代碼：");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("選課失敗，課程代碼不得為空白。");
            return;
        }

        Course course = findCourseByCode(courseList, code);

        if (course == null) {
            System.out.println("選課失敗，找不到課程代碼：" + code);
            return;
        }

        if (course.registerStudent()) {
            System.out.println("選課成功：" + course.getName());
            System.out.println("目前選課人數：" + course.getCurrentStudents());
        } else {
            System.out.println("選課失敗，課程已額滿。");
        }
    }

    public static void withdrawCourse(
            Scanner scanner,
            ArrayList<Course> courseList) {

        System.out.print("請輸入要退選的課程代碼：");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("退選失敗，課程代碼不得為空白。");
            return;
        }

        Course course = findCourseByCode(courseList, code);

        if (course == null) {
            System.out.println("退選失敗，找不到課程代碼：" + code);
            return;
        }

        if (course.withdrawStudent()) {
            System.out.println("退選成功：" + course.getName());
            System.out.println("目前選課人數：" + course.getCurrentStudents());
        } else {
            System.out.println("退選失敗，目前沒有學生選擇此課程。");
        }
    }

    public static void deleteCourse(
            Scanner scanner,
            ArrayList<Course> courseList) {

        System.out.print("請輸入要刪除的課程代碼：");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("刪除失敗，課程代碼不得為空白。");
            return;
        }

        Course course = findCourseByCode(courseList, code);

        if (course == null) {
            System.out.println("刪除失敗，找不到課程代碼：" + code);
            return;
        }

        if (course.getCurrentStudents() > 0) {
            System.out.println("刪除失敗，此課程目前仍有學生選課。");
            return;
        }

        courseList.remove(course);
        System.out.println("課程刪除成功：" + course.getName());
    }

    public static void searchCourse(
            Scanner scanner,
            ArrayList<Course> courseList) {

        System.out.print("請輸入要搜尋的課程代碼：");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("搜尋失敗，課程代碼不得為空白。");
            return;
        }

        Course course = findCourseByCode(courseList, code);

        if (course == null) {
            System.out.println("找不到課程代碼：" + code);
        } else {
            System.out.println("找到課程：");
            System.out.println(course);
        }
    }

    public static void showAllCourses(
            ArrayList<Course> courseList) {

        System.out.println("\n===== 全部課程 =====");

        if (courseList.isEmpty()) {
            System.out.println("目前沒有任何課程資料。");
            return;
        }

        for (int i = 0; i < courseList.size(); i++) {
            System.out.println((i + 1) + ". " + courseList.get(i));
        }
    }

    public static void showStatistics(
            ArrayList<Course> courseList) {

        System.out.println("\n===== 課程統計 =====");

        if (courseList.isEmpty()) {
            System.out.println("目前沒有任何課程資料。");
            System.out.println("總課程數：0");
            System.out.println("總選課人次：0");
            System.out.println("額滿課程：無");
            return;
        }

        int totalStudents = calculateTotalStudents(courseList);

        System.out.println("總課程數：" + courseList.size());
        System.out.println("總選課人次：" + totalStudents);
        System.out.println("\n===== 額滿課程 =====");

        boolean hasFullCourse = false;

        for (Course course : courseList) {
            if (course.isFull()) {
                System.out.println(course);
                hasFullCourse = true;
            }
        }

        if (!hasFullCourse) {
            System.out.println("目前沒有額滿課程。");
        }
    }

    public static int calculateTotalStudents(
            ArrayList<Course> courseList) {

        int total = 0;

        for (Course course : courseList) {
            total += course.getCurrentStudents();
        }

        return total;
    }

    public static Course findCourseByCode(
            ArrayList<Course> courseList,
            String targetCode) {

        for (Course course : courseList) {
            if (course.getCode().equalsIgnoreCase(targetCode)) {
                return course;
            }
        }

        return null;
    }

    public static int readInteger(
            Scanner scanner,
            String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("輸入錯誤，請輸入整數。");
            }
        }
    }
}