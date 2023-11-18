import java.util.PriorityQueue;

public class Lab6Q2 {
    static class Univeristy implements Comparable<Univeristy> {
        int rank;
        String name;

        public Univeristy(int rank, String name) {
            this.rank = rank;
            this.name = name;
        }

        public int compareTo(Univeristy b) {
            if (this.rank == b.rank)
                return this.name.compareTo(b.name);
            return this.rank - b.rank;
        }

        @Override
        public String toString() {
            return "Univeristy [rank=" + rank + ", name=" + name + "]";
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Univeristy> pq = new PriorityQueue<>();
        pq.add(new Univeristy(5, "Fam"));
        pq.add(new Univeristy(5, "AMartin"));
        System.out.println(pq);
    }
}