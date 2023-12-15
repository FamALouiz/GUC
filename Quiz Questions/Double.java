public class Double {
    public static void main(String[] args) {
        LinkedList ls = new LinkedList();
        int[] arr = new int[] { 8, 2 };
        for (int i : arr) {
            ls.insertLast(i);
        }
        System.out.println(ls);
        ls.d();

        System.out.println(ls);
    }

    static class Node {
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

    static class LinkedList {
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

        public LinkedList d() {
            Node temp = this.head;
            int num = 0;
            while (temp != null) {
                num = num * 10 + (Integer) temp.data;
                temp = temp.next;
            }
            num *= 2;
            int num2 = 0;
            while (num > 0) {
                num2 = (num % 10) + num2 * 10;
                num /= 10;
            }
            Node head = new Node(num2 % 10);
            Node tempHead = head;
            num2 /= 10;
            while (num2 > 0) {
                tempHead.next = new Node(num2 % 10);
                num2 /= 10;
                tempHead = tempHead.next;
            }
            this.head = head;
            return null;
        }
    }
}
