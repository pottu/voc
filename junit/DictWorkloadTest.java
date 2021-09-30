import org.python.types.Dict;

public class DictWorkloadTest {
    public DictWorkloadTest() {
    }
    public void workload() {
        long MAX = 500000;
        Dict d = new Dict();
        for(long i=0; i < MAX; i++) {
            d.__setitem__(org.python.types.Int.getInt(i), new org.python.types.Str("hej"));
        }
        for(long i=0; i < MAX; i++) {
            if(i % 2== 0) {
                d.__getitem__(org.python.types.Int.getInt(i));
            }
            else {
                d.__setitem__(org.python.types.Int.getInt(i), new org.python.types.Str("lol"));
            }
        }
        for(long i=0; i < MAX; i++) {
            if(i % 5== 0) {
                d.__delitem__(org.python.types.Int.getInt(i));
            }
        }

    }
    public static void main(String[] args) {
       DictWorkloadTest d = new DictWorkloadTest();
       d.workload();
    }
}
