public class Lab1012 {
    public static void main(String[] args) {
        NodeT n1 = new NodeT(20, 50);
        NodeT n2 = new NodeT(10, 10);
        NodeT n3 = new NodeT(25, 30);
        NodeT n4 = new NodeT(80, 90);
        NodeT n5 = new NodeT(60, 70);
        NodeT n6 = new NodeT(91, 100);
        n1.left = n2;
        n1.middle = n3;
        n1.right = n4;
        n4.left = n5;
        n4.right = n6;
        Tree t = new Tree(n1);
        System.out.println(t.search(100).leftVal + " " + t.search(100).rightVal);
    }
}

class NodeT {
    int leftVal, rightVal;
    NodeT left, middle, right;

    public NodeT(int leftVal, int rightVal) {
        this.leftVal = leftVal;
        this.rightVal = rightVal;
    }
}

class Tree {
    NodeT root;

    public Tree(NodeT root) {
        this.root = root;
    }

    public NodeT search(int x) {
        return searchHelper(null, this.root, x);
    }

    public NodeT searchHelper(NodeT parent, NodeT n, int x) {
        if (n == null && x <= parent.rightVal && x >= parent.leftVal) {
            return parent;
        } else if (n == null) {
            return null;
        }

        if (x < n.leftVal) {
            return searchHelper(n, n.left, x);
        } else if (x > n.rightVal) {
            return searchHelper(n, n.right, x);
        } else {
            return searchHelper(n, n.middle, x);
        }
    }
}