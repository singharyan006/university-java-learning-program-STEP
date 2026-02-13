public class DeleteAllOccurrencesDLL {
    // Node class
    static class Node {
        int data;
        Node prev, next;

        Node(int data) {
            this.data = data;
            this.prev = this.next = null;
        }
    }

    Node head;

    // Append node to the end
    public void append(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null)
            current = current.next;

        current.next = newNode;
        newNode.prev = current;
    }

    // Delete all occurrences of a given value
    public void deleteAll(int value) {
        Node current = head;

        while (current != null) {
            if (current.data == value) {
                Node prevNode = current.prev;
                Node nextNode = current.next;

                if (prevNode != null)
                    prevNode.next = nextNode;
                else
                    head = nextNode; // Deleting head

                if (nextNode != null)
                    nextNode.prev = prevNode;
            }
            current = current.next;
        }
    }

    // Display the list
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) System.out.print(" ⇄ ");
            current = current.next;
        }
        System.out.println();
    }

    // Main method
    public static void main(String[] args) {
        DeleteAllOccurrencesDLL list = new DeleteAllOccurrencesDLL();

        // Initial list: 10 ⇄ 20 ⇄ 30 ⇄ 20 ⇄ 40
        list.append(10);
        list.append(20);
        list.append(30);
        list.append(20);
        list.append(40);

        System.out.print("Original List: ");
        list.display();

        // Delete all 20s
        list.deleteAll(20);

        System.out.print("After deleting 20: ");
        list.display();
    }
}
