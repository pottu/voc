import org.python.types.Dict;

public class DictWorkloadTest {
    public DictWorkloadTest() {
    }
    public void workload() {
        long MAX = 400000;
        Dict d = new Dict();
        String s = "hejasdaasd";
        for(long i=0; i < MAX; i++) {
            d.__setitem__(new org.python.types.Str(s+i), new org.python.types.Str("hej"));
        }
        for(long i=0; i < MAX; i++) {
            if(i % 2== 0) {
                d.__getitem__(new org.python.types.Str(s+i));
            }
            else {
                d.__setitem__(new org.python.types.Str(s+i), new org.python.types.Str("lol"));
            }
        }
        for(long i=0; i < MAX; i++) {
            if(i % 5== 0) {
                d.__delitem__(new org.python.types.Str(s+i));
            }
        }

    }
    public static void main(String[] args) {
       DictWorkloadTest d = new DictWorkloadTest();
       d.workload();
    }
}
