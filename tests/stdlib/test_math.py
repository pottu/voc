from unittest import expectedFailure
from ..utils import TranspileTestCase


class MathModuleTests(TranspileTestCase):

    ############################################################
    # __doc__
    def test___doc__(self):
        self.assertCodeExecution("""
            import math
            print(math.__doc__)
            """)

    #######################################################
    # isqrt
    @expectedFailure
    def test_isqrt_string(self):
        self.assertCodeExecution("""
            import math
            math.isqrt('hej')
            """)

    @expectedFailure
    def test_isqrt_empty(self):
        self.assertCodeExecution("""
            import math
            math.isqrt()
            """)

    @expectedFailure
    def test_isqrt_none(self):
        self.assertCodeExecution("""
            import math
            math.isqrt(None)
            """)

    @expectedFailure
    def test_isqrt_negative_int(self):
        self.assertCodeExecution("""
            import math
            math.isqrt(-5)
            """)

    @expectedFailure
    def test_isqrt_negative_float(self):
        self.assertCodeExecution("""
            import math
            math.isqrt(-5.8)
            """)

    @expectedFailure
    def test_isqrt_positive_float(self):
        self.assertCodeExecution("""
            import math
            math.isqrt(5.8)
            """)

    def test_isqrt_possible_outcomes(self):
        self.assertCodeExecution("""
            import math
            print(math.isqrt(5))
            print(math.isqrt(16))
            print(math.isqrt(0))
            print(math.isqrt(1))
            print(math.isqrt(20129310))
            try:
                math.isqrt("hej")
            except Exception as e:
                print(e)
            try:
                math.isqrt(5.5)
            except Exception as e:
                print(e)
            try:
                math.isqrt([123,52])
            except Exception as e:
                print(e)
            try:
                math.isqrt(None)
            except Exception as e:
                print(e)
            try:
                math.isqrt(-5)
            except Exception as e:
                print(e)
            try:
                math.isqrt((5,2,3))
            except Exception as e:
                print(e)
            """)

    ############################################################
    # ceil
    def test_ceil_float(self):
        self.assertCodeExecution("""
            import math
            print(math.ceil(3.5))
            """)

    def test_ceil_int(self):
        self.assertCodeExecution("""
            import math
            print(math.ceil(3))
            """)

    @expectedFailure
    def test_ceil_no_args(self):
        self.assertCodeExecution("""
            import math
            print(math.ceil())
            """)

    @expectedFailure
    def test_ceil_multiple_args(self):
        self.assertCodeExecution("""
            import math
            print(math.ceil(1.5, 2))
            """)

    @expectedFailure
    def test_ceil_string(self):
        self.assertCodeExecution("""
            import math
            print(math.ceil("test"))
            """)

    def test_ceil_override(self):
        self.assertCodeExecution("""
        import math
        class Test:
            number = 3.5
            def __ceil__(self):
                return math.ceil(self.number)
        test = Test()
        print(math.ceil(test))
        """)

    def test_ceil_exception_msg(self):
        self.assertCodeExecution("""
        import math
        try:
            math.ceil("str")
        except Exception as e:
            print(e)
        """)

    def test_ceil_exception_msg2(self):
        self.assertCodeExecution("""
        import math
        try:
            ("str").__ceil__()
        except Exception as e:
            print(e)
        """)
