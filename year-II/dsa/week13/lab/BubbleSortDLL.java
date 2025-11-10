public class BubbleSortDLL {
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

    // Append node to end
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

    // Bubble sort the list
    public void bubbleSort() {
        if (head == null) return;

        boolean swapped;
        do {
            swapped = false;
            Node current = head;

            while (current.next != null) {
                if (current.data > current.next.data) {
                    // Swap data
                    int temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
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
        BubbleSortDLL list = new BubbleSortDLL();

        // Input: [40 ⇄ 10 ⇄ 30 ⇄ 20]
        list.append(40);
        list.append(10);
        list.append(30);
        list.append(20);

        System.out.print("Original List: ");
        list.display();

        list.bubbleSort();

        System.out.print("Sorted List: ");
        list.display();
    }
}
