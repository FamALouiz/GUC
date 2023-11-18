public class LabTest2 {
    public static boolean check(QueueObj q) {
        int a = 0, b = 0;
        for (int i = 0; i < q.size(); i++) {
            if (((Character) q.peek()) == 'a' || (Character) q.peek() == 'A') {
                a++;
            } else if ((Character) q.peek() == 'b' || (Character) q.peek() == 'B') {
                b++;
            }
            q.enqueue(q.dequeue());
        }
        return a == b;
    }

    public static void main(String[] args) {
        QueueObj q = new QueueObj(10);
        q.enqueue('a');
        q.enqueue('b');
        q.enqueue('s');
        q.enqueue('t');
        q.enqueue('b');
        q.enqueue('a');
        System.out.println(LabTest2.check(q));
    }
}
