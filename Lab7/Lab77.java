
public class Lab77 {

    public static void main(String[] args) {
        LinkedList<Integer> ls = new LinkedList<>();
        for (int i = 0; i < 9; i++) {
            ls.insertFirst(i);
            ls.insertLast(i);
        }
        System.out.println(ls);
        ls.addOnes();
        System.out.println(ls);
        ls.addOnesRec();
        System.out.println(ls);
    }
}

class Node<T> {
    T data;
    Node<T> next;

    Node(T data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList<T> {

    private Node<T> head, tail;

    LinkedList() {
        this.head = this.tail = null;
    }

    public void insertLast(T data) {
        if (this.head == null) {
            this.head = this.tail = new Node<>(data);
            return;
        }
        this.tail.next = new Node<>(data);
        this.tail = this.tail.next;
    }

    public void insertFirst(T data) {
        if (this.head == null) {
            this.head = this.tail = new Node<>(data);
            return;
        }
        Node<T> temp = new Node<>(data);
        temp.next = this.head;
        this.head = temp;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T temp = this.head.data;
        this.head = this.head.next;
        return temp;
    }

    public T removeLast() {
        Node<T> temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        T res = temp.next.data;
        temp.next = null;
        return res;
    }

    public void reverse() {
        if (isEmpty())
            return;
        Node<T> prev = null, curr = head, next = head.next;
        while (curr != null) {
            curr.next = prev;
            prev = curr;
            curr = next;
            if (next != null)
                next = next.next;
        }
        this.head = prev;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public String toString() {
        String res = "";
        Node<T> temp = head;
        while (temp != null) {
            res += temp.data + " ";
            temp = temp.next;
        }
        return res;
    }

    public void addOnes() {
        Node<T> temp = this.head;
        while (temp != null) {
            Integer data = (Integer) temp.data;
            temp.data = (T) (Integer) (data + 1);
            temp = temp.next;
        }
    }

    public void addOnesRec() {
        helper(this.head);
    }

    public void helper(Node<T> curr) {
        if (curr == null) {
            return;
        }
        Integer temp = (Integer) curr.data;
        curr.data = (T) (Integer) (temp + 1);
        helper(curr.next);
    }
}