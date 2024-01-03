public class BinaryHashMap {
    java.util.LinkedList<Integer>[] map;

    public BinaryHashMap() {
        map = new java.util.LinkedList[2];
        map[0] = new java.util.LinkedList<Integer>();
        map[1] = new java.util.LinkedList<Integer>();
    }

    public int hashValue(int x) {
        return x & 1;
    }

    public void insert(int x) {
        map[hashValue(x)].add(x);
    }

    public boolean search(int x) {
        java.util.LinkedList<Integer> curr = map[hashValue(x)];

        for (int i = 0; i < curr.size(); i++) {
            if (curr.get(i) == x) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        BinaryHashMap bhm = new BinaryHashMap();
        bhm.insert(2);
        bhm.insert(9);
        bhm.insert(10);
        bhm.insert(7);
        bhm.insert(1);
        bhm.insert(3);
        bhm.insert(4);
        bhm.insert(4);
        System.out.println(bhm.search(2));
        System.out.println(bhm.search(111));
    }
}