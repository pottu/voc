from unittest import expectedFailure
from ..utils import TranspileTestCase


class MathModuleTests(TranspileTestCase):

    ############################################################
    # ceil
    def test_ceil_float(self):
        self.assertCodeExecution("""
            import math
            print(math.ceil(3.5))
            """)
