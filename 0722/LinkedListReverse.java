public class LinkedListReverse {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        Node emptyHead = null;

        System.out.println("空串列反轉前：");
        printList(emptyHead);

        emptyHead = reverse(emptyHead);

        System.out.println("空串列反轉後：");
        printList(emptyHead);

        Node singleHead = new Node(10);

        System.out.println("\n單一節點反轉前：");
        printList(singleHead);

        singleHead = reverse(singleHead);

        System.out.println("單一節點反轉後：");
        printList(singleHead);

        Node head = null;
        head = append(head, 10);
        head = append(head, 20);
        head = append(head, 30);
        head = append(head, 40);

        System.out.println("\n多節點串列反轉前：");
        printList(head);

        head = reverse(head);

        System.out.println("多節點串列反轉後：");
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

    public static Node reverse(Node head) {
        Node previous = null;
        Node current = head;

        while (current != null) {
            Node nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }

        return previous;
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