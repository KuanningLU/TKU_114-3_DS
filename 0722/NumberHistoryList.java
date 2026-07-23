public class NumberHistoryList {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;

    public void addFirst(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;

        System.out.println("前端新增：" + data);
    }

    public void addLast(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }

        System.out.println("尾端新增：" + data);
    }

    public boolean contains(int target) {
        Node current = head;

        while (current != null) {
            if (current.data == target) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public boolean remove(int target) {
        if (head == null) {
            System.out.println("串列為空，無法刪除：" + target);
            return false;
        }

        if (head.data == target) {
            head = head.next;
            System.out.println("已刪除：" + target);
            return true;
        }

        Node current = head;

        while (current.next != null) {
            if (current.next.data == target) {
                current.next = current.next.next;
                System.out.println("已刪除：" + target);
                return true;
            }

            current = current.next;
        }

        System.out.println("找不到資料：" + target);
        return false;
    }

    public void printList() {
        if (head == null) {
            System.out.println("目前串列：空串列");
            return;
        }

        System.out.print("目前串列：");

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

    public int getSize() {
        int size = 0;
        Node current = head;

        while (current != null) {
            size++;
            current = current.next;
        }

        return size;
    }

    public int getSum() {
        int sum = 0;
        Node current = head;

        while (current != null) {
            sum += current.data;
            current = current.next;
        }

        return sum;
    }

    public Integer getMax() {
        if (head == null) {
            return null;
        }

        int max = head.data;
        Node current = head.next;

        while (current != null) {
            if (current.data > max) {
                max = current.data;
            }

            current = current.next;
        }

        return max;
    }

    public Integer getMin() {
        if (head == null) {
            return null;
        }

        int min = head.data;
        Node current = head.next;

        while (current != null) {
            if (current.data < min) {
                min = current.data;
            }

            current = current.next;
        }

        return min;
    }

    public void printStatistics() {
        System.out.println("節點數量：" + getSize());
        System.out.println("數字總和：" + getSum());

        if (head == null) {
            System.out.println("最大值：無");
            System.out.println("最小值：無");
        } else {
            System.out.println("最大值：" + getMax());
            System.out.println("最小值：" + getMin());
        }
    }

    public static void main(String[] args) {
        NumberHistoryList list = new NumberHistoryList();

        System.out.println("初始狀態：");
        list.printList();
        list.printStatistics();

        System.out.println("\n第 1 次操作");
        list.addLast(20);
        list.printList();

        System.out.println("\n第 2 次操作");
        list.addFirst(10);
        list.printList();

        System.out.println("\n第 3 次操作");
        list.addLast(30);
        list.printList();

        System.out.println("\n第 4 次操作");
        list.addFirst(5);
        list.printList();

        System.out.println("\n第 5 次操作");
        System.out.println("搜尋 20：" + list.contains(20));
        list.printList();

        System.out.println("\n第 6 次操作");
        System.out.println("搜尋 99：" + list.contains(99));
        list.printList();

        System.out.println("\n第 7 次操作");
        list.remove(10);
        list.printList();

        System.out.println("\n第 8 次操作");
        list.remove(99);
        list.printList();

        System.out.println("\n最後統計結果：");
        list.printStatistics();
    }
}