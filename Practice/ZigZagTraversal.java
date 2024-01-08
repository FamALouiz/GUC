package Practice;

import java.util.LinkedList;
import java.util.Queue;

public class ZigZagTraversal {
    public static void main(String[] args) {
        TreeNode[] nodes = new TreeNode[6];
        for (int i = 0; i < 6; i++) {
            nodes[i] = new TreeNode((char) ('A' + i));
        }
        nodes[0].left = nodes[1];
        nodes[0].right = nodes[2];
        nodes[1].left = nodes[3];
        nodes[2].left = nodes[4];
        nodes[2].right = nodes[5];
        BTree tree = new BTree();
        tree.root = nodes[0];
        tree.reverseOrder();
    }
}

class TreeNode {

    public Character data;
    public TreeNode left, right;

    public TreeNode(Character data) {
        this(data, null, null);
    }

    public TreeNode(Character data, TreeNode left, TreeNode right) {

        this.data = data;
        this.left = left;
        this.right = right;
    }
}

class BTree {
    public TreeNode root;

    public BTree() {
        root = null;
    }

    public void add(Character key) {
        TreeNode current = root, parent = null;
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
            root = new TreeNode(key);

        else {
            if (key.compareTo(parent.data) < 0)
                parent.left = new TreeNode(key);

            else
                parent.right = new TreeNode(key);
        }
    }

    public boolean delete(Character key) {
        if (root == null)
            return false;
        TreeNode current = root;
        TreeNode parent = root;
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

        TreeNode substitute = null;
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
            TreeNode successor = current.right;
            TreeNode successorParent = current;
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
        java.util.Stack<TreeNode> globalStack = new java.util.Stack<TreeNode>();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println(
                "......................................................");
        while (isRowEmpty == false) {
            java.util.Stack<TreeNode> localStack = new java.util.Stack<TreeNode>();
            isRowEmpty = true;

            for (int j = 0; j < nBlanks; j++)
                System.out.print(' ');

            while (globalStack.isEmpty() == false) {
                TreeNode temp = globalStack.pop();
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

    public void zigzag() {
        if (root == null) {
            return;
        }
        java.util.LinkedList<TreeNode> ls = new java.util.LinkedList<>();
        ls.add(this.root);
        zigzagHelper(this.root, true, ls);
    }

    public void zigzagHelper(TreeNode curr, boolean right, java.util.LinkedList<TreeNode> ls) {
        if (ls.isEmpty() || curr == null) {
            return;
        }
        if (right) {
            if (curr.right != null)
                ls.add(curr.right);
            if (curr.left != null)
                ls.add(curr.left);
            System.out.print(ls.poll().data + " ");
            zigzagHelper(curr.left, !right, ls);
            zigzagHelper(curr.right, !right, ls);
        } else {
            if (curr.left != null)
                ls.add(curr.left);
            if (curr.right != null)
                ls.add(curr.right);
            System.out.print(ls.poll().data + " ");
            zigzagHelper(curr.right, !right, ls);
            zigzagHelper(curr.left, !right, ls);
        }
    }

    public void reverseOrder() {
        String s = reverseOrderHelper(this.root);
        System.out.println(s);
    }

    public String reverseOrderHelper(TreeNode curr) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(curr);
        String res = "";
        while (!q.isEmpty()) {
            TreeNode n = q.poll();
            if (n == null) {
                continue;
            }
            res = n.data + " " + res;
            q.add(n.right);
            q.add(n.left);
        }
        return res;
    }
}