package Final2019;

public class Final2019 {

    public static void reverseK(LinkedList ls, int k) {
        if (size(ls) == 1 || size(ls) < k) {
            return;
        }
        int size = size(ls);
        int iter = size / k;
        SLLNode prev = null, curr = ls.head, next = ls.head.next;
        while (iter-- > 0) {
            SLLNode ancor = curr;
            for (int i = 0; i < k - 1; i++) {
                curr.next = prev;
                prev = curr;
                curr = next;
                next = next.next;
            }
            SLLNode temp = curr;
            for (int i = 0; i < k - 1; i++) {
                temp = temp.next;
            }
            ancor.next = temp;
            prev = curr;
            curr = next;
            next = next.next;
        }
    }

    public static int size(LinkedList ls) {
        SLLNode temp = ls.head;
        int size = 0;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        return size;
    }

    public static LinkedList inter(LinkedList l1, LinkedList l2) {
        LinkedList res = new LinkedList();
        SLLNode resHead = res.head;
        SLLNode temp1 = l1.head, temp2 = l2.head;

        while (temp1 != null && temp2 != null) {
            if (temp1.data.equals(temp2.data)) {
                if (resHead == null) {
                    resHead = new SLLNode(temp2.data);
                    res.head = resHead;
                } else {
                    resHead.next = new SLLNode(temp2.data);
                    resHead = resHead.next;
                }
                temp1 = temp1.next;
            } else {
                if (((Comparable) temp1.data).compareTo((Comparable) temp2.data) > 0) {
                    temp2 = temp2.next;
                } else {
                    temp1 = temp1.next;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LinkedList l1 = new LinkedList();
        LinkedList l2 = new LinkedList();

        int[] nums1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        for (int i : nums1) {
            l1.insertLast(i);
        }
        System.out.println(l1);
        reverseK(l1, 3);
        System.out.println(l1);
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
