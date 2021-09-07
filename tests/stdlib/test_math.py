from unittest import expectedFailure
from ..utils import TranspileTestCase


class MathModuleTests(TranspileTestCase):

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
    def test_isqrt_positve_float(self):
        self.assertCodeExecution("""
            import math
            math.isqrt(5.8)
            """)

    def test_isqrt_possible_outcomes(self):
        self.assertCodeExecution("""
            import math
            math.isqrt(5)
            math.isqrt(16)
            math.isqrt(0)
            math.isqrt(1)
            math.isqrt(20129310)
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
