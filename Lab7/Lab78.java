public class Lab78 {
    public static LinkedList cut(LinkedList x) {
        LinkedList temp = new LinkedList();
        int c = 0;
        while (!x.isEmpty()) {
            temp.insertFirst(x.removeFirst());
            c++;
        }
        for (int i = 0; i < c; i++) {
            x.insertFirst(temp.removeFirst());
        }
        for (int i = 0; i < c / 2 + 1; i++) {
            x.insertFirst(x.removeLast());
        }
        return x;
    }

    public static void main(String[] args) {
        LinkedList ls = new LinkedList(), ls2 = new LinkedList();
        int[] arr = { 1, 2, 3 };
        for (int i : arr) {
            ls.insertLast(i);
            ls2.insertLast(i);
        }
        ls = cut(ls);
        System.out.println(ls);
        ls2.cutList();
        System.out.println(ls2);
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

    public void cutList() {
        int size = 0;
        Node temp = this.head;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        Node last = this.head;
        while (last.next != null) {
            last = last.next;
        }
        temp = this.head;
        for (int i = 0; i < size / 2 - 1; i++) {
            temp = temp.next;
        }
        Node temp2 = temp.next;
        temp.next = null;
        last.next = this.head;
        this.head = temp2;
    }
}
