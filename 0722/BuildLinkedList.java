public class BuildLinkedList {

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

        printList(head);

        System.out.println("節點數量：" + countNodes(head));
        System.out.println("節點總和：" + sumNodes(head));

        int target = 30;
        Node result = search(head, target);

        if (result != null) {
            System.out.println("找到資料：" + result.data);
        } else {
            System.out.println("找不到資料：" + target);
        }
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

    public static void printList(Node head) {
        if (head == null) {
            System.out.println("鏈結串列為空。");
            return;
        }

        System.out.print("鏈結串列：");

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

    public static int countNodes(Node head) {
        int count = 0;
        Node current = head;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }

    public static int sumNodes(Node head) {
        int sum = 0;
        Node current = head;

        while (current != null) {
            sum += current.data;
            current = current.next;
        }

        return sum;
    }

    public static Node search(Node head, int target) {
        Node current = head;

        while (current != null) {
            if (current.data == target) {
                return current;
            }

            current = current.next;
        }

        return null;
    }
}