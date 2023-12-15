package Lab8;

public class Lab85 {
    public static void main(String[] args) {
        BTree tree = new BTree();
        tree.add(1);
        tree.add(3);
        tree.add(5);
        tree.add(2);
        tree.add(-1);
        tree.add(-5);
        tree.add(3);
        tree.add(3);
        tree.add(100);
        tree.add(-11);
        tree.add(-100);
        tree.add(25);
        tree.add(2);
        System.out.println(tree.size());
        System.out.println(tree.numLeaves());
        System.out.println(tree.sum());
        tree.displayTree();
        System.out.println(tree.isBST());
        System.out.println(tree.numLeftChildNodes());
        System.out.println(tree.countOccur(3));
        System.out.println(tree.hasDups(3));
        System.out.println(tree.hasDups(-1));
        tree.mirror();
        tree.displayTree();
        tree.mirror();
        tree.displayTree();
        System.out.println(tree.oddNodes());
        System.out.println(tree.depth(-5) + " " + tree.depth(1) + " " + tree.depth(3) + " " + tree.depth(-100));
        BTree temp = tree.doubleValues();
        BTree temp2 = tree.doubleValues();
        temp.displayTree();
        temp2.displayTree();
        System.out.println(temp.equal(temp2));
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
    private Node root;

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

    // ============================== Added Methods ==============================
    public int size() {
        return sizeHelper(root);
    }

    public int sizeHelper(Node n) {
        if (n == null) {
            return 0;
        } else {
            return 1 + sizeHelper(n.right) + sizeHelper(n.left);
        }
    }

    public int numLeaves() {
        return numLeavesHelper(root);
    }

    public int numLeavesHelper(Node n) {
        if (n == null) {
            return 0;
        }
        if (n.right == null && n.left == null) {
            return 1;
        } else {
            return numLeavesHelper(n.right) + numLeavesHelper(n.left);
        }
    }

    public int sum() {
        return sumHelper(root);
    }

    public int sumHelper(Node n) {
        if (n == null)
            return 0;
        else
            return (Integer) n.data + sumHelper(n.right) + sumHelper(n.left);
    }

    public boolean isBST() {
        return isBSTHelper(root);
    }

    public boolean isBSTHelper(Node n) {
        if (n == null) {
            return true;
        }
        if (n.right != null && n.left != null) {
            if ((Integer) n.data < (Integer) n.right.data && (Integer) n.data >= (Integer) n.left.data) {
                return true && isBSTHelper(n.right) && isBSTHelper(n.left);
            } else {
                return false;
            }
        } else if (n.right != null) {
            if ((Integer) n.data < (Integer) n.right.data) {
                return true && isBSTHelper(n.right) && isBSTHelper(n.left);
            } else {
                return false;
            }
        } else if (n.left != null) {
            if ((Integer) n.data >= (Integer) n.left.data) {
                return true && isBSTHelper(n.right) && isBSTHelper(n.left);
            } else {
                return false;
            }
        }
        return true;
    }

    public int numLeftChildNodes() {
        return numLeftChildNodesHelper(root);
    }

    public int numLeftChildNodesHelper(Node n) {
        if (n == null) {
            return 0;
        }
        if (n.left != null && n.right == null) {
            return 1 + numLeftChildNodesHelper(n.left);
        } else if (n.left != null && n.right != null) {
            return numLeftChildNodesHelper(n.right) + numLeftChildNodesHelper(n.left);
        } else if (n.right != null && n.left == null) {
            return numLeftChildNodesHelper(n.right) + numLeftChildNodesHelper(n.left);
        } else {
            return 0;
        }
    }

    public int countOccur(Comparable key) {
        return countOccurHelper(root, key);
    }

    public int countOccurHelper(Node n, Comparable key) {
        if (n == null) {
            return 0;
        }
        if (n.data.equals(key)) {
            return 1 + countOccurHelper(n.left, key) + countOccurHelper(n.right, key);
        } else {
            return countOccurHelper(n.left, key) + countOccurHelper(n.right, key);
        }
    }

    public boolean hasDups(Comparable key) {
        return !(countOccur(key) < 2);
    }

    public void mirror() {
        mirrorHelper(root);
    }

    public void mirrorHelper(Node n) {
        if (n == null) {
            return;
        }
        Node temp = n.right;
        n.right = n.left;
        n.left = temp;
        mirrorHelper(n.left);
        mirrorHelper(n.right);
    }

    public String oddNodes() {
        return oddNodesHelper(root);
    }

    public String oddNodesHelper(Node n) {
        if (n == null) {
            return "";
        }
        if (((Integer) n.data) % 2 == 1) {
            return n.data + " " + oddNodesHelper(n.right) + oddNodesHelper(n.left);
        } else {
            return oddNodesHelper(n.right) + oddNodesHelper(n.left);
        }
    }

    public int depth(Comparable key) {
        return depthHelper(root, key, 0);

    }

    public int depthHelper(Node n, Comparable key, int depth) {
        if (n == null) {
            return -1;
        }
        if (n.data.equals(key)) {
            return depth;
        } else {
            return Math.max(depthHelper(n.left, key, depth + 1), depthHelper(n.right, key, depth + 1));
        }
    }

    public BTree doubleValues() {
        BTree temp = new BTree();
        addAll(temp, root);
        doubleValuesHelper(temp.root);
        return temp;
    }

    public void addAll(BTree tree, Node n) {
        if (n == null) {
            return;
        }
        tree.add(n.data);
        addAll(tree, n.right);
        addAll(tree, n.left);
    }

    public void doubleValuesHelper(Node n) {
        if (n == null) {
            return;
        }
        n.data = 2 * (Integer) n.data;
        doubleValuesHelper(n.right);
        doubleValuesHelper(n.left);
    }

    public boolean equal(BTree t2) {
        return equalHelper(root, t2.root);
    }

    public boolean equalHelper(Node n1, Node n2) {
        if (n1 == null && n2 == null) {
            return true;
        } else if (n1 == null || n2 == null) {
            return false;
        }

        if (n1.data.equals(n2.data)) {
            return true && equalHelper(n1.right, n2.right) && equalHelper(n1.left, n2.left);
        } else {
            return false;
        }
    }
}