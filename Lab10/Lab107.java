public class Lab107 {
    public static void main(String[] args) {
        BTree t = new BTree();
        int[] nums = new int[] { 50, 20, 75, 5, 25, 66, 80 };
        for (int i : nums) {
            t.add(i);
        }
        t.printRange(20, 90);
    }
}
