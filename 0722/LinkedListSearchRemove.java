public class LinkedListSearchRemove {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        Node head = null;

        head = append(head, 10);
        head = append(head, 20);
        head = append(head, 30);
        head = append(head, 40);

        System.out.println("原始鏈結串列：");
        printList(head);

        System.out.println("\n搜尋 30：" + contains(head, 30));
        System.out.println("搜尋 99：" + contains(head, 99));

        System.out.println("\n刪除 head 節點 10：");
        head = removeValue(head, 10);
        printList(head);

        System.out.println("\n刪除中間節點 30：");
        head = removeValue(head, 30);
        printList(head);

        System.out.println("\n刪除最後節點 40：");
        head = removeValue(head, 40);
        printList(head);

        System.out.println("\n刪除不存在的資料 99：");
        head = removeValue(head, 99);
        printList(head);

        System.out.println("\n刪除剩餘節點 20：");
        head = removeValue(head, 20);
        printList(head);

        System.out.println("\n測試空串列刪除 10：");
        head = removeValue(head, 10);
        printList(head);
    }

    public static Node append(Node head, int data) {
        Node newNode = new Node(data);

        if (head == null) {
            return newNode;
        }

        Node current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
        return head;
    }

    public static boolean contains(Node head, int target) {
        Node current = head;

        while (current != null) {
            if (current.data == target) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public static Node removeValue(Node head, int target) {
        if (head == null) {
            System.out.println("鏈結串列為空，無法刪除。");
            return null;
        }

        if (head.data == target) {
            System.out.println("已刪除資料：" + target);
            return head.next;
        }

        Node current = head;

        while (current.next != null) {
            if (current.next.data == target) {
                current.next = current.next.next;
                System.out.println("已刪除資料：" + target);
                return head;
            }

            current = current.next;
        }

        System.out.println("找不到資料：" + target);
        return head;
    }

    public static void printList(Node head) {
        if (head == null) {
            System.out.println("鏈結串列為空。");
            return;
        }

        Node current = head;

        while (current != null) {
            System.out.print(current.data);

            if (current.next != null) {
                System.out.print(" -> ");
            }

            current = current.next;
        }

        System.out.println();
    }
}