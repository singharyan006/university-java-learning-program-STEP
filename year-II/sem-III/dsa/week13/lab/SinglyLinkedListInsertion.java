public class SinglyLinkedListInsertion {
    // Node class
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Head of the list
    Node head;

    // Insert at specified position
    public void insertAtPosition(int data, int position) {
        Node newNode = new Node(data);

        if (position < 1) {
            System.out.println("Invalid position. Must be >= 1.");
            return;
        }

        if (position == 1) {
            newNode.next = head;
            head = newNode;
            return;
        }

        Node current = head;
        int count = 1;

        while (current != null && count < position - 1) {
            current = current.next;
            count++;
        }

        if (current == null) {
            System.out.println("Invalid position. List is shorter than " + position);
            return;
        }

        newNode.next = current.next;
        current.next = newNode;
    }

    // Display the list
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) System.out.print(" → ");
            current = current.next;
        }
        System.out.println();
    }

    // Main method for testing
    public static void main(String[] args) {
        SinglyLinkedListInsertion list = new SinglyLinkedListInsertion();

        // Initial list: 10 → 20 → 30 → 40
        list.insertAtPosition(10, 1);
        list.insertAtPosition(20, 2);
        list.insertAtPosition(30, 3);
        list.insertAtPosition(40, 4);
        System.out.print("Initial List: ");
        list.display();

        // Insert 50 at position 3
        list.insertAtPosition(50, 3);
        System.out.print("After inserting 50 at position 3: ");
        list.display();
    }
}
