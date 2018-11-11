package list;

/**
 * http://javabypatel.blogspot.com/2015/12/detect-loop-in-linked-list.html
 */
public class DetectLoopInLinkedList {
    private Node startNode;

    public static void main(String[] args) {
        DetectLoopInLinkedList detectLoopInLinkedList = new DetectLoopInLinkedList();

        Node n1 = new Node(10);
        Node n2 = new Node(20);
        Node n3 = new Node(30);
        Node n4 = new Node(40);
        Node n5 = new Node(50);
        Node n6 = new Node(60);
        Node n7 = new Node(70);
        Node n8 = new Node(80);

        detectLoopInLinkedList.startNode = n1;

        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n4.setNext(n5);
        n5.setNext(n6);
        n6.setNext(n7);
        n7.setNext(n8);
        n8.setNext(n6);

        if (isLoopPresent(detectLoopInLinkedList.startNode)) {
            System.out.println("Loop is present in list");
        } else {
            System.out.println("No Loop present in list");
        }
    }

    private static boolean isLoopPresent(Node startNode) {
        Node slowPointer = startNode; // Initially ptr1 is at starting location.
        Node fastPointer = startNode; // Initially ptr2 is at starting location.

        while (fastPointer != null && fastPointer.getNext() != null) { // If ptr2 encounters NULL, it means there is no Loop in Linked list.
            slowPointer = slowPointer.getNext(); // ptr1 moving one node at at time
            fastPointer = fastPointer.getNext().getNext(); // ptr2 moving two nodes at at time

            if (slowPointer == fastPointer) // if ptr1 and ptr2 meets, it means linked list contains loop.
                return true;
        }
        return false;
    }

    static class Node {

        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}