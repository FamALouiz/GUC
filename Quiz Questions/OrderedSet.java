import java.util.LinkedList;

public class OrderedSet {

    BTree bTree;

    public OrderedSet() {
        this.bTree = new BTree();
    }

    public void add(Comparable item) {
        if (bTree == null) {
            bTree.add(item);
            return;
        }
        if (contains(item)) {
            return;
        }
        bTree.add(item);
    }

    public boolean contains(Comparable item) {
        if (bTree.search(item) != null) {
            return true;
        }
        return false;
    }

    public int size() {
        return sizeHelper(bTree.root);
    }

    public int sizeHelper(Node curr) {
        if (curr == null) {
            return 0;
        }
        return 1 + sizeHelper(curr.left) + sizeHelper(curr.right);
    }

    public void remove(Comparable item) {
        if (contains(item)) {
            bTree.delete(item);
        }
    }

    public LinkedList<Comparable> toList() {
        LinkedList<Comparable> ls = new LinkedList<>();
        toListHelper(bTree.root, ls);
        return ls;
    }

    public void toListHelper(Node n, LinkedList<Comparable> ls) {
        if (n == null)
            return;
        toListHelper(n.left, ls);
        ls.add(n.data);
        toListHelper(n.right, ls);
    }

    public static void main(String[] args) {
        OrderedSet s = new OrderedSet();
        s.add(2);
        s.add(1);
        s.add(2);
        s.add(5);
        s.add(7);
        s.add(5);
        s.add(2);
        s.remove(2);
        s.remove(2);
        s.add(2);
        System.out.println(s.size());
        System.out.println(s.toList());
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

    public Node search(Comparable item) {
        return helperSearch(this.root, item);
    }

    public Node helperSearch(Node n, Comparable item) {
        if (n == null) {
            return null;
        }
        if (n.data.equals(item)) {
            return n;
        }
        if (item.compareTo(n.data) > 0) {
            return helperSearch(n.right, item);
        } else {
            return helperSearch(n.left, item);
        }
    }
}
