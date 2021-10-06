from unittest import expectedFailure
from ..utils import TranspileTestCase


class DateModuleTests(TranspileTestCase):

    def test__set_year(self):
        self.assertCodeExecution("""
            from datetime import date
            d = date(2021,10,6)
            try:
                d.year = 2022
            except Exception as e:
                print(e)
            """)

    def test__set_month(self):
        self.assertCodeExecution("""
            from datetime import date
            d = date(2021,10,6)
            try:
                d.month = 1
            except Exception as e:
                print(e)
            """)

    def test__set_day(self):
        self.assertCodeExecution("""
            from datetime import date
            d = date(2021,10,6)
            try:
                d.day = 3
            except Exception as e:
                print(e)
            """)

    def test__set_invalid_attr(self):
        self.assertCodeExecution("""
            from datetime import date
            d = date(2021,10,6)
            try:
                d.foo = 2022
            except Exception as e:
                print(e)
            try:
                d.bar = "hello"
            except Exception as e:
                print(e)
            try:
                d.baz = (1,2)
            except Exception as e:
                print(e)
            """)

    @expectedFailure
    def test__set_should_fail_1(self):
        self.assertCodeExecution("""
            from datetime import date
            d = date(2021,10,6)
            d.day = 3
            """)

    @expectedFailure
    def test__set_should_fail_2(self):
        self.assertCodeExecution("""
            from datetime import date
            d = date(2021,10,6)
            d.foo = 3
            """)
