public class Lab79 {
    public static void frontBackSplit(LinkedList ls) {
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
        if ((size & 1) == 1) {
            for (int i = 0; i < (size + 1) / 2; i++) {
                front.insertLast(ls.getFirst());
                ls.insertLast(ls.removeFirst());
            }
            for (int i = 0; i < size - (size + 1) / 2; i++) {
                back.insertLast(ls.getFirst());
                ls.insertLast(ls.removeFirst());
            }
        } else {
            for (int i = 0; i < (size) / 2; i++) {
                front.insertLast(ls.getFirst());
                ls.insertLast(ls.removeFirst());
            }
            for (int i = 0; i < size - size / 2; i++) {
                back.insertLast(ls.getFirst());
                ls.insertLast(ls.removeFirst());
            }
        }
        System.out.println("First is: " + front + "Second is: " + back);
    }

    public static void main(String[] args) {
        int[] arr = { 2, 1, 2, 3, 4, 1, 2, 4, 1, 2, 4 };
        LinkedList ls = new LinkedList();
        for (int i : arr) {
            ls.insertLast(i);
        }
        frontBackSplit(ls);
        System.out.println(ls);
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
