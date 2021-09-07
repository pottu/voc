package python;

@org.python.Module(__doc__ = "This module provides access to the mathematical functions\ndefined by the C standard.")
public class math extends org.python.types.Module {
    @org.python.Attribute
    public static org.python.Object __file__ = new org.python.types.Str("python/common/python/math.java");
    @org.python.Attribute
    public static org.python.Object __package__ = new org.python.types.Str("math");

    @org.python.Method(__doc__ = "ceil(x) -> number\n\n"
            + "Return the ceiling of x as an Integral.\n\nThis is the smallest integer >= x.", args = { "x" })
    public static org.python.Object ceil(org.python.Object x) {
        return x.__ceil__();
    }
}
