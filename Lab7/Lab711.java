public class Lab711 {
    public static LinkedList merge(LinkedList ls1, LinkedList ls2) {
        LinkedList temp = new LinkedList();
        while (!ls1.isEmpty()) {
            temp.insertLast(ls1.removeFirst());
        }
        while (!ls2.isEmpty()) {
            temp.insertLast(ls2.removeFirst());
        }
        return temp;
    }

    public static int getSize(LinkedList ls1) {
        LinkedList temp = new LinkedList();
        int size = 0;
        while (!ls1.isEmpty()) {
            temp.insertLast(ls1.removeFirst());
            size++;
        }
        while (!temp.isEmpty()) {
            ls1.insertFirst(temp.removeLast());
        }
        return size;
    }

    public static boolean equals(LinkedList ls1, LinkedList ls2) {
        int size1 = getSize(ls1);
        int size2 = getSize(ls2);
        if (size1 != size2)
            return false;
        LinkedList temp = new LinkedList();
        while (!ls1.isEmpty()) {
            boolean flag = false;
            Object curr = ls1.removeFirst();
            LinkedList temp2 = new LinkedList();
            while (!ls2.isEmpty()) {
                Object curr2 = ls2.removeFirst();
                if (curr.equals(curr2)) {
                    flag = true;
                }
                temp2.insertLast(curr2);
            }
            temp.insertLast(curr);
            while (!temp2.isEmpty()) {
                ls2.insertLast(temp2.removeFirst());
            }
            if (flag == false) {
                while (!temp.isEmpty()) {
                    ls1.insertLast(temp.removeLast());
                }
                return false;
            }
        }
        while (!temp.isEmpty()) {
            ls1.insertLast(temp.removeLast());
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr1 = { 1, 2, 3, 4, 5 };
        int[] arr2 = { 1, 2, 3, 5, 4, 6 };
        LinkedList ls1 = new LinkedList(), ls2 = new LinkedList();
        for (int i : arr1) {
            ls1.insertLast(i);
        }
        for (int i : arr2) {
            ls2.insertLast(i);
        }
        System.out.println(equals(ls1, ls2));
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
