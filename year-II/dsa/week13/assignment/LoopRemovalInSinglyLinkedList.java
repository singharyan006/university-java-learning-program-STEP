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

    public boolean detectLoop() {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                removeLoop(slow);
                return true;
            }
        }
        return false;
    }

    private void removeLoop(Node loopNode) {
        Node ptr1 = head;
        Node ptr2 = loopNode;

        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        Node temp = ptr2;
        while (temp.next != ptr1) {
            temp = temp.next;
        }

        temp.next = null;
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

public class LoopRemovalInSinglyLinkedList {
    public static void main(String[] args) {

        SinglyLinkedList list = new SinglyLinkedList();

        list.head = new Node(10);
        list.head.next = new Node(20);
        list.head.next.next = new Node(30);
        list.head.next.next.next = new Node(40);
        list.head.next.next.next.next = new Node(50);

        list.head.next.next.next.next.next = list.head.next.next;

        System.out.println("Loop detection in progress...");
        boolean found = list.detectLoop();

        if (found)
            System.out.println("Loop detected and removed");
        else
            System.out.println("No loop found");

        System.out.println("List after loop removal:");
        list.display();
    }
}
