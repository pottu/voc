package python;
import java.lang.Math;

@org.python.Module(
        __doc__ =
                "TODO: Write me!"
)
public class math extends org.python.types.Module {
    public math() {
        super();
    }


    @org.python.Attribute
    public static org.python.Object __file__ = new org.python.types.Str("python/common/python/math.java");
    // @org.python.Attribute
    // public static org.python.Object __loader__ = org.python.types.NoneType.NONE;
    @org.python.Attribute
    public static org.python.Object __name__ = new org.python.types.Str("math");
    // @org.python.Attribute
    // public static org.python.Object __package__ = new org.python.types.Str("");
    // @org.python.Attribute
    // public static org.python.Object __spec__ = org.python.types.NoneType.NONE;

    @org.python.Method(
         __doc__ = "Return the integer square root of the nonnegative integer n. This is the floor of the exact square root of n, or equivalently the greatest integer a such that a² ≤ n.",
         args = {"n"}
    )
    public static org.python.Object isqrt(org.python.Object n) {
       if (n instanceof org.python.types.Int) {
           long n_val = ((org.python.types.Int) n.__int__()).value;
           if (n_val < 0) {
             throw new org.python.exceptions.ValueError("math.isqrt only supports nonnegative integers.");
           }
           return org.python.types.Int.getInt(((long) Math.floor(Math.sqrt(n_val))));
       }
       throw new org.python.exceptions.TypeError("math.isqrt only supports integers.");
    }

}
