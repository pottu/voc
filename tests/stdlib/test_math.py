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
