import math

from unittest import expectedFailure

from ..utils import TranspileTestCase


class MathModuleTests(TranspileTestCase):

    ############################################################
    # ceil
    @expectedFailure
    def test_ceil_no_args(self):
        self.assertCodeExecution("""
            import math
            print(math.ceil())
            """)

    @expectedFailure
    def test_ceil_string(self):
        self.assertCodeExecution("""
            import math
            print(math.ceil("test"))
            """)

    def test_ceil_float(self):
        self.assertCodeExecution("""
            import math
            print(math.ceil(3.5))
            """)

    def test_ceil_float(self):
        self.assertCodeExecution("""
            import math
            print(math.ceil(3))
            """)
