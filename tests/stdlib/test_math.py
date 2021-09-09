from unittest import expectedFailure
from ..utils import TranspileTestCase


class MathModuleTests(TranspileTestCase):

    #######################################################
    # __doc__
    def test_math___doc__(self):
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
