public class Lab710 {
    public static void alternatingSplit(LinkedList ls) {
        LinkedList front = new LinkedList(), back = new LinkedList();
        int size = 0;
        LinkedList temp = new LinkedList();
        while (!ls.isEmpty()) {
            temp.insertFirst(ls.removeFirst());
            size++;
        }
        for (int i = 0; i < size; i++) {
            ls.insertFirst(temp.removeFirst());
        }
        for (int i = 0; i < size; i++) {
            if ((i & 1) == 1) {
                back.insertLast(ls.getFirst());
            } else {
                front.insertLast(ls.getFirst());
            }
            ls.insertLast(ls.removeFirst());
        }
        System.out.println("First is: " + front + "Second is: " + back);
    }

    public static void main(String[] args) {
        char[] arr = { 'a', 'b', 'c', 'd', 'e' };
        LinkedList ls = new LinkedList();
        for (char i : arr) {
            ls.insertLast(i);
        }
        alternatingSplit(ls);
    }
}

class Node {
    public Object data;
    public Node next;

    public Node(Object o) {
        this.data = o;
        this.next = null;
    }

    public String toString() {
        return data.toString();
    }
}

class LinkedList {
    private Node head;

    public LinkedList() {
        head = null;
    }

    public void insertFirst(Object o) {
        Node newLink = new Node(o);
        newLink.next = head;
        head = newLink;
    }

    public Object removeFirst() {
        Object res = head.data;
        head = head.next;
        return res;
    }

    public Object getFirst() {
        return head.data;
    }

    public void insertLast(Object o) {
        Node newLink = new Node(o);
        if (head == null) {
            head = newLink;
            return;
        }
        Node current = head;
        while (current.next != null)
            current = current.next;
        current.next = newLink;
    }

    public Object removeLast() {
        if (head.next == null) {
            Object res = head.data;
            head = null;
            return res;
        }
        Node current = head;
        while (current.next.next != null)
            current = current.next;
        Object res = current.next.data;
        current.next = null;
        return res;
    }

    public Object getLast() {
        Node current = head;
        while (current.next != null)
            current = current.next;
        return current.data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public String toString() {
        if (head == null)
            return "[ ]";
        String res = "[ " + head.data;
        Node current = head.next;
        while (current != null) {
            res += ", " + current.data;
            current = current.next;
        }
        res += " ]";
        return res;
    }

}
