package Final2023;

public class FInal2023 {

    public static void pred(BTree tree, int key) {
        predHelper(tree.root, key);
    }

    public static void predHelper(Node n, int key) {
        Node prev = null;
        while (n != null && (Integer) n.data != key) {
            prev = n;
            if (n.data.compareTo(key) > 0) {
                n = n.left;
            } else {
                n = n.right;
            }
        }
        if (n == null) {
            System.out.println("Not in tree");
            return;
        }
        if (n.left != null)
            System.out.println(n.left.data);
        else if (prev.data.compareTo(n.data) < 0) {
            System.out.println((Integer) prev.data);
        } else {
            System.out.println("NO");
        }
    }

    public static void main(String[] args) {
        BTree tree = new BTree();
        int[] nums = { 15, 20, 16, 25, 10, 8, 12 };
        for (int i : nums)
            tree.add(i);
        int[] ans = { 8, 10, 12, 20, 22 };
        for (int i : ans)
            pred(tree, i);
        tree.displayTree();
        tree.bottomUp();
    }
}

class Node {

    public Comparable data;
    public Node left, right;

    public Node(Comparable data) {
        this(data, null, null);
    }

    public Node(Comparable data, Node left, Node right) {

        this.data = data;
        this.left = left;
        this.right = right;
    }
}

class BTree {
    public Node root;

    public BTree() {
        root = null;
    }

    public void add(Comparable key) {
        Node current = root, parent = null;
        while (current != null) {
            if (key.compareTo(current.data) < 0) {
                parent = current;
                current = current.left;
            }

            else {
                parent = current;
                current = current.right;
            }
        }

        if (parent == null)
            root = new Node(key);

        else {
            if (key.compareTo(parent.data) < 0)
                parent.left = new Node(key);

            else
                parent.right = new Node(key);
        }
    }

    public boolean delete(Comparable key) {
        if (root == null)
            return false;
        Node current = root;
        Node parent = root;
        boolean right = true;
        // searching for the node to be deleted
        while (key.compareTo(current.data) != 0) {
            if (key.compareTo(current.data) < 0) {
                right = false;
                parent = current;
                current = current.left;
            } else {
                right = true;
                parent = current;
                current = current.right;
            }
            if (current == null)
                return false;
        }

        Node substitute = null;
        // case 1: Node to be deleted has no children
        if (current.left == null && current.right == null)
            substitute = null;

        // case 2: Node to be deleted has one child
        else if (current.left == null)
            substitute = current.right;
        else if (current.right == null)
            substitute = current.left;
        else // case 3: Node to be deleted has two children
        {
            Node successor = current.right;
            Node successorParent = current;
            // searching for the inorder successor of the node to be deleted
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            substitute = successor;
            if (successorParent == current) {
                if (successor.right == null)
                    successorParent.right = null;
                else
                    successorParent.right = successor.right;
            } else {
                if (successor.right == null)
                    successorParent.left = null;
                else
                    successorParent.left = successor.right;
            }
            successor.right = current.right;
            successor.left = current.left;
            substitute = successor;
        } // case 3 done
        if (current == root) // Replacing the deleted node
            root = substitute;
        else if (right)
            parent.right = substitute;
        else
            parent.left = substitute;
        return true;

    }

    public void displayTree() {
        java.util.Stack<Node> globalStack = new java.util.Stack<Node>();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println(
                "......................................................");
        while (isRowEmpty == false) {
            java.util.Stack<Node> localStack = new java.util.Stack<Node>();
            isRowEmpty = true;

            for (int j = 0; j < nBlanks; j++)
                System.out.print(' ');

            while (globalStack.isEmpty() == false) {
                Node temp = globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.data);
                    localStack.push(temp.left);
                    localStack.push(temp.right);

                    if (temp.left != null ||
                            temp.right != null)
                        isRowEmpty = false;
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++)
                    System.out.print(' ');
            } // end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop());
        } // end while isRowEmpty is false
        System.out.println(
                "......................................................");
    }

    public BTree treeIntersect(BTree t2) {
        BTree curr = new BTree();
        treeIntersectHelper(this.root, t2.root, curr);
        return curr;
    }

    public void treeIntersectHelper(Node n1, Node n2, BTree curr) {
        if (n1 == null) {
            return;
        }
        if (n1.data.equals(n2.data)) {
            curr.add(n1.data);
        }
        treeIntersectHelper(n1.left, n2.left, curr);
        treeIntersectHelper(n1.right, n2.right, curr);
    }

    public void bottomUp() {
        java.util.PriorityQueue<Pair> ls = new java.util.PriorityQueue<>((a, b) -> b.level - a.level);
        java.util.LinkedList<Pair> nodes = new java.util.LinkedList<>();
        nodes.add(new Pair(this.root, 0));
        while (!nodes.isEmpty()) {
            Pair curr = nodes.removeFirst();
            ls.add(curr);
            if (curr.n.left != null)
                nodes.add(new Pair(curr.n.left, curr.level + 1));
            if (curr.n.right != null)
                nodes.add(new Pair(curr.n.right, curr.level + 1));
        }
        int size = ls.size();
        for (int i = 0; i < size; i++) {
            System.out.print(ls.poll().n.data + " ");
        }
        System.out.println();
    }

}

class Pair {
    Node n;
    int level;

    Pair(Node n, int level) {
        this.n = n;
        this.level = level;
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
    private SLLNode head;

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

    public LinkedList d() {
        SLLNode temp = this.head;
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
        SLLNode head = new SLLNode(num2 % 10);
        SLLNode tempHead = head;
        num2 /= 10;
        while (num2 > 0) {
            tempHead.next = new SLLNode(num2 % 10);
            num2 /= 10;
            tempHead = tempHead.next;
        }
        this.head = head;
        return null;
    }

    public void sum() {
        SLLNode prev = null;
        SLLNode temp1 = this.head;
        while (temp1 != null && temp1.next != null) {
            int sum = 0;
            SLLNode temp2 = temp1.next;
            while ((Integer) temp2.data != 0) {
                sum += (Integer) temp2.data;
                temp2 = temp2.next;
            }
            temp1.data = sum;
            temp1.next = temp2;
            prev = temp1;
            temp1 = temp1.next;
        }
        prev.next = null;
    }

    public BTree toTree() {
        BTree tree = new BTree();
        toTreeHelper(this, 0, size(), tree);
        return tree;
    }

    public void toTreeHelper(LinkedList n, int start, int end, BTree tree) {
        SLLNode temp = this.head;
        int mid = start + (end - start) / 2;
        if (mid > size() || mid < 0) {
            return;
        }
        for (int i = 0; i < mid; i++) {
            temp = temp.next;
        }
        System.out.println(temp.data);
        tree.add((Comparable) temp.data);
        if (start >= end)
            return;
        toTreeHelper(n, start + mid + 1, end, tree);
        toTreeHelper(n, start, end - mid - 1, tree);
    }

    public int size() {
        SLLNode temp = this.head;
        int size = 0;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        return size;
    }
}