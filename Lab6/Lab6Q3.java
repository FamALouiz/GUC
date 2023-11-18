import java.util.PriorityQueue;

public class Lab6Q3 {
    static class Patient implements Comparable<Patient> {
        int severity;
        String name;

        Patient(String name, int severity) {
            this.name = name;
            this.severity = severity;
        }

        @Override
        public int compareTo(Patient b) {
            if (this.severity == b.severity)
                return this.name.compareTo(b.name);
            return this.severity - b.severity;
        }

        @Override
        public String toString() {
            return "Patient [severity=" + severity + ", name=" + name + "]";
        }
    }

    static class EmergencyRoom {
        PriorityQueue<Patient> pq;

        public EmergencyRoom() {
            this.pq = new PriorityQueue<>();
        }

        public void newPatient(String name, int severity) {
            pq.add(new Patient(name, severity));
        }

        public Patient nexPatient() {
            return pq.poll();
        }
    }

    public static void main(String[] args) {
        EmergencyRoom er = new EmergencyRoom();
        er.newPatient("Fam", 10);
        er.newPatient("Martin", 50);
        er.newPatient("Awad", 10);
        System.out.println(er.nexPatient());
    }
}
