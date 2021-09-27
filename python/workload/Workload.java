package workload;

import org.python.types.List;

public class Workload {
    private static int NUM_RUNS = 1;
    private static int NUM_ELEMENTS = 210000;

    public static void doWork() {
        List l = new List();

        for (int i = 0; i < NUM_ELEMENTS; i++) {
            int rand = (int)(Math.random() * 100 + 1);
            l.insert(org.python.types.Int.getInt(0), org.python.types.Int.getInt(rand));
        }

        l.sort(null,null);

        for (int i = 0; i < NUM_ELEMENTS; i++) {
            l.pop(org.python.types.Int.getInt(0));
        }
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < NUM_RUNS; i++) {
            doWork();
        }
        long endTime = System.currentTimeMillis();
        long avgTime = (endTime - startTime) / NUM_RUNS;
        System.out.println("Time (ms): " + avgTime);
    }
}
