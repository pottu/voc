package python;

@org.python.Module(
        __doc__ =
        "This module provides access to the mathematical functions defined by the C standard.\n"
        + "\n"
        + "These functions cannot be used with complex numbers; use the functions of the same\n"
        + "name from the cmath module if you require support for complex numbers.\n"
        + "The distinction between functions which support complex numbers and those which don’t\n"
        + "is made since most users do not want to learn quite as much mathematics as required\n"
        + "to understand complex numbers. Receiving an exception instead of a complex result\n"
        + "allows earlier detection of the unexpected complex number used as a parameter,\n"
        + "so that the programmer can determine how and why it was generated in the first place.")

public class math extends org.python.types.Module {
    public math() {
        super();
    }

    @org.python.Attribute
    public static org.python.Object __file__ = new org.python.types.Str("python/common/python/math.java");
    @org.python.Attribute
    public static org.python.Object __name__ = new org.python.types.Str("math");


    @org.python.Method(__doc__ = "isqrt(n) -> int\n\n"
            + "Return the integer square root of the nonnegative integer n.\n"
            + "This is the floor of the exact square root of n\n"
            + "or equivalently the greatest integer a such that a² ≤ n.",
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
    }
}
