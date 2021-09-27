package workload;

import org.python.types.List;


public class Workload {
    private static int NUM_ELEMENTS = 100000;
    public static void main(String[] args) {
        List l = new List();
        for (int i = 0; i < NUM_ELEMENTS; i++) {
            int rand = (int)(Math.random() * 100 + 1);
            l.append(org.python.types.Int.getInt(rand));
            l.sort(null,null);
        }
        System.out.println("DONE");
    }
}
