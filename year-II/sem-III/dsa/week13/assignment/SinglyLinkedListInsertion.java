class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class SinglyLinkedList {
    Node head;

    public void insertAtPosition(int data, int position) {
        Node newNode = new Node(data);

        if (position == 1) {
            newNode.next = head;
            head = newNode;
            return;
        }

        Node temp = head;
        int currentPos = 1;

        while (temp != null && currentPos < position - 1) {
            temp = temp.next;
            currentPos++;
        }

        if (temp == null)
            return;

        newNode.next = temp.next;
        temp.next = newNode;
    }

    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.next != null) System.out.print(" -> ");
            temp = temp.next;
        }
        System.out.println();
    }
}

public class SinglyLinkedListInsertion {
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        list.insertAtPosition(10, 1);
        list.insertAtPosition(20, 2);
        list.insertAtPosition(30, 3);
        list.insertAtPosition(40, 4);

        System.out.println("Initial List:");
        list.display();

        java.util.Scanner sc = new java.util.Scanner(System.in);

        System.out.print("Enter data to insert: ");
        int data = sc.nextInt();

        System.out.print("Enter position: ");
        int position = sc.nextInt();

        list.insertAtPosition(data, position);

        System.out.println("Updated List:");
        list.display();

        sc.close();
    }
}
