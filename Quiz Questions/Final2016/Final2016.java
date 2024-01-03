package Final2016;

public class Final2016 {
    public static void deleteTwoSmallest(LinkedList ls) {
        SLLNode temp1 = ls.head;
        SLLNode temp2 = ls.head.next;
        int min = (Integer) temp1.data + (Integer) temp2.data;
        int minIdx = 0;
        for (int i = 0; i < size(ls) - 1; i++) {
            if ((Integer) temp1.data + (Integer) temp2.data < min) {
                min = (Integer) temp1.data + (Integer) temp2.data;
                minIdx = i;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        temp1 = ls.head;
        temp2 = ls.head.next;
        for (int i = 0; i < minIdx - 1; i++) {
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        int sum = (Integer) temp1.next.data + (Integer) temp2.next.data;
        temp1.next = temp2.next.next;
        temp1 = ls.head;
        for (int i = 0; i < size(ls) - 1; i++) {
            temp1 = temp1.next;
        }
        temp2.data = sum;
        temp2.next = null;
        temp1.next = temp2;
    }

    public static int size(LinkedList ls) {
        SLLNode temp = ls.head;
        int size = 0;
        while (temp != null) {
            temp = temp.next;
            size++;
        }
        return size;
    }

    public static void main(String[] args) {
        LinkedList ls = new LinkedList();
        int[] nums = { 1, 4, -10, 6, -5 };
        for (int i : nums) {
            ls.insertLast(i);
        }
        System.out.println(ls);
        deleteTwoSmallest(ls);
        System.out.println(ls);
    }
}

class SLLNode {
    public Object data;
    public SLLNode next;

    public SLLNode(Object o) {
        this.data = o;
        this.next = null;
    }

    public String toString() {
        return data.toString();
    }
}

class LinkedList {
    public SLLNode head;

    public LinkedList() {
        head = null;
    }

    public void insertFirst(Object o) {
        SLLNode newLink = new SLLNode(o);
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
        SLLNode newLink = new SLLNode(o);
        if (head == null) {
            head = newLink;
            return;
        }
        SLLNode current = head;
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
        SLLNode current = head;
        while (current.next.next != null)
            current = current.next;
        Object res = current.next.data;
        current.next = null;
        return res;
    }

    public Object getLast() {
        SLLNode current = head;
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
        SLLNode current = head.next;
        while (current != null) {
            res += ", " + current.data;
            current = current.next;
        }
        res += " ]";
        return res;
    }

}
