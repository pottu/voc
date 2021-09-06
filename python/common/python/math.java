package python;

@org.python.Module(__doc__ = "")
public class math extends org.python.types.Module {
    @org.python.Attribute
    public static org.python.Object __file__ = new org.python.types.Str("python/common/python/math.java");
    @org.python.Attribute
    public static org.python.Object __loader__ = org.python.types.NoneType.NONE; // TODO
    @org.python.Attribute
    public static org.python.Object __name__ = new org.python.types.Str("time");
    @org.python.Attribute
    public static org.python.Object __package__ = new org.python.types.Str("");
    @org.python.Attribute
    public static org.python.Object __spec__ = org.python.types.NoneType.NONE; // TODO

    @org.python.Method(__doc__ = "")
    public static org.python.Object ceil(org.python.Object number) {
        throw new org.python.exceptions.NotImplementedError("math.ceil() has not been implemented.");
    }
}
