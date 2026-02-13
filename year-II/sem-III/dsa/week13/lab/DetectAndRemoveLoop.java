public class DetectAndRemoveLoop {
    // Node class
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    Node head;

    // Utility: Add node to end
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
    }

    // Utility: Create loop for testing
    public void createLoop(int position) {
        if (position <= 0) return;
        Node loopNode = head;
        for (int i = 1; i < position && loopNode != null; i++)
            loopNode = loopNode.next;

        Node tail = head;
        while (tail.next != null)
            tail = tail.next;

        if (loopNode != null)
            tail.next = loopNode;
    }

    // Detect and remove loop using Floyd’s algorithm
    public void detectAndRemoveLoop() {
        Node slow = head, fast = head;
        boolean loopExists = false;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                loopExists = true;
                break;
            }
        }

        if (!loopExists) {
            System.out.println("No loop detected.");
            return;
        }

        // Find start of loop
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        // Find last node in loop
        Node loopEnd = fast;
        while (loopEnd.next != slow)
            loopEnd = loopEnd.next;

        // Break the loop
        loopEnd.next = null;
        System.out.println("Loop detected and removed.");
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

    // Main method
    public static void main(String[] args) {
        DetectAndRemoveLoop list = new DetectAndRemoveLoop();

        // Create list: 10 → 20 → 30 → 40 → 50
        list.append(10);
        list.append(20);
        list.append(30);
        list.append(40);
        list.append(50);

        // Create loop: 50 → 30
        list.createLoop(3);

        // Display before removal (looped list can't be printed safely)
        System.out.println("Before loop removal: (loop exists, can't display safely)");

        // Detect and remove loop
        list.detectAndRemoveLoop();

        // Display after removal
        System.out.print("After loop removal: ");
        list.display();
    }
}
