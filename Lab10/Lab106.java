public class Lab106 {
    public static void main(String[] args) {
        BTree bt = new BTree();
        int[] nums = new int[] { 4, 5, 2, 1, 3, 11, 10 };

        for (int i : nums) {
            bt.add(i);
        }

    }
}
