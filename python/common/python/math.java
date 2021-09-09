package python;

@org.python.Module(
        __doc__ =
        "This module provides access to the mathematical functions\n"
        + "defined by the C standard.")

public class math extends org.python.types.Module {
    public math() {
        super();
    }

    @org.python.Attribute
    public static org.python.Object __file__ = new org.python.types.Str("python/common/python/math.java");
    @org.python.Attribute
    public static org.python.Object __name__ = new org.python.types.Str("math");


    @org.python.Method(__doc__ = "isqrt(n) -> int\n\n"
            + "Return the integer part of the square root of the input.",
            args = { "n" })
    public static org.python.Object isqrt(org.python.Object n) {
        if (n instanceof org.python.types.Int) {
            long n_val = ((org.python.types.Int) n.__int__()).value;
            if (n_val < 0) {
                throw new org.python.exceptions.ValueError("isqrt() argument must be nonnegative");
            }
            return org.python.types.Int.getInt(((long) Math.floor(Math.sqrt(n_val))));
        }
        throw new org.python.exceptions.TypeError("'" + n.typeName() + "' object cannot be interpreted as an integer");

    @org.python.Method(__doc__ = "ceil(x) -> number\n\n"
            + "Return the ceiling of x as an Integral.\n\nThis is the smallest integer >= x.", args = { "x" })
    public static org.python.Object ceil(org.python.Object x) {
        try {
            return x.__ceil__();
        } catch (org.python.exceptions.AttributeError ae) {
            throw new org.python.exceptions.TypeError("must be real number, not " + x.typeName());
        }
    }
}
