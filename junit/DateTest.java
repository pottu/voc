import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.python.stdlib.datetime.Date;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DateTest {
    public org.python.Object[] createArgs(long year, long month, long day) {
        org.python.types.Int d = org.python.types.Int.getInt(day);
        org.python.types.Int m = org.python.types.Int.getInt(month);
        org.python.types.Int y = org.python.types.Int.getInt(year);
        org.python.Object[] args = {y, m, d};
        return args;
    }


    public Date createDate(long year, long month, long day) {
        org.python.types.Int d = org.python.types.Int.getInt(day);
        org.python.types.Int m = org.python.types.Int.getInt(month);
        org.python.types.Int y = org.python.types.Int.getInt(year);
        org.python.Object[] args = {y, m, d};
        return new Date(args, Collections.emptyMap());
    }

    @Test
    public void testDateConstructorSuccess1() {
        // Only args used.
        org.python.types.Int y = org.python.types.Int.getInt(2021);
        org.python.types.Int m = org.python.types.Int.getInt(9);
        org.python.types.Int d = org.python.types.Int.getInt(14);
        org.python.Object[] args = {y, m, d};
        java.util.Map<java.lang.String, org.python.Object> kwargs = Collections.emptyMap();
        assertDoesNotThrow(() -> new Date(args, kwargs));
    }

    @Test
    public void testDateConstructorSuccess2() {
        // Mixed usage of kwargs and args.
        org.python.types.Int y = org.python.types.Int.getInt(2021);
        org.python.types.Int m = org.python.types.Int.getInt(9);
        org.python.Object[] args = {y, m};
        java.util.Map<java.lang.String, org.python.Object> kwargs = new java.util.HashMap<java.lang.String, org.python.Object>() {
            {
                put("day", org.python.types.Int.getInt(2));
            }
        };
        assertDoesNotThrow(() -> new Date(args, kwargs));
    }

    @Test
    public void testDateConstructorSuccess3() {
        // Only kwargs used.
        org.python.Object[] args = {};
        java.util.Map<java.lang.String, org.python.Object> kwargs = new java.util.HashMap<java.lang.String, org.python.Object>() {
            {
                put("year", org.python.types.Int.getInt(2021));
                put("month", org.python.types.Int.getInt(9));
                put("day", org.python.types.Int.getInt(14));
            }
        };
        assertDoesNotThrow(() -> new Date(args, kwargs));
    }

    @Test
    public void test__repr__() {
        Date d = createDate(2021, 9, 14);
        assertEquals("datetime.date(2021, 9, 14)", d.__repr__().value);
    }

    @Test
    public void testYearMonthDay() {
        Date d = createDate(2021, 9, 14);
        assertEquals(org.python.types.Int.getInt(2021), d.year);
        assertEquals(org.python.types.Int.getInt(9), d.month);
        assertEquals(org.python.types.Int.getInt(14), d.day);
    }

    @Test
    public void testMax() {
        Date d = createDate(python.datetime.MAXYEAR.value, 12, 31);
        assertTrue(((org.python.types.Bool) d.__eq__(Date.max)).value);
    }

    @Test
    public void testMin() {
        Date d = createDate(python.datetime.MINYEAR.value, 1, 1);
        assertTrue(((org.python.types.Bool) d.__eq__(Date.min)).value);
    }

    @Test
    public void testToday() {
        java.time.LocalDateTime expected = java.time.LocalDateTime.now();
        Date actual = (Date) Date.today();
        assertEquals(org.python.types.Int.getInt(expected.getYear()), actual.year);
        assertEquals(org.python.types.Int.getInt(expected.getMonthValue()), actual.month);
        assertEquals(org.python.types.Int.getInt(expected.getDayOfMonth()), actual.day);
    }

    @Test
    public void testCtime() {
        Date d = createDate(2021, 9, 14);
        assertEquals("Tue Sep 14 00:00:00 2021", ((org.python.types.Str) d.ctime()).value);
    }

    @Test
    public void testWeekday() {
        Date d = createDate(2021, 9, 14);
        assertEquals(1, ((org.python.types.Int) d.weekday()).value);
    }

    @Test
    public void testDateConstructorOutOfRangeException() {
        org.python.types.Int year = org.python.types.Int.getInt(2010);
        org.python.types.Int month = org.python.types.Int.getInt(1);
        org.python.types.Int day = org.python.types.Int.getInt(35);
        org.python.Object[] args = {year, month, day};
        Exception exception = assertThrows(org.python.exceptions.ValueError.class, () -> new Date(args, Collections.emptyMap()));
        assertEquals("day is out of range for month", exception.getMessage());
    }

    @Test
    public void testDateConstructor4argsException() {
        org.python.types.Int num = org.python.types.Int.getInt(1);
        org.python.Object[] args = {num, num, num, num};
        Exception exception = assertThrows(org.python.exceptions.TypeError.class, () -> new Date(args, Collections.emptyMap()));
        assertEquals("function takes at most 3 arguments (4 given)", exception.getMessage());
    }

    @Test
    public void testDateConstructor3argsException() {
        org.python.types.Int num = org.python.types.Int.getInt(1);
        org.python.Object[] args = {num, num};
        Map<String, org.python.Object> kwargs = new HashMap<String, org.python.Object>() {
            {
                put("year", num);
            }
        };
        Exception exception = assertThrows(org.python.exceptions.SyntaxError.class, () -> new Date(args, kwargs));
        assertEquals("positional argument follows keyword argument", exception.getMessage());
    }

    @Test
    public void testDateConstructor3argsException2() {
        org.python.types.Int num = org.python.types.Int.getInt(1);
        org.python.types.Str str = new org.python.types.Str("test");
        org.python.Object[] args = {num, num, str};
        Exception exception = assertThrows(org.python.exceptions.TypeError.class, () -> new Date(args, Collections.emptyMap()));
        assertEquals("an integer is required (got type str)", exception.getMessage());
    }

    @Test
    public void testDateConstructor2argsException() {
        org.python.types.Int num = org.python.types.Int.getInt(1);
        org.python.types.Str str = new org.python.types.Str("test");
        org.python.Object[] args = {str, num};
        Exception exception = assertThrows(org.python.exceptions.TypeError.class, () -> new Date(args, Collections.emptyMap()));
        assertEquals("an integer is required (got type str)", exception.getMessage());
    }

    @Test
    public void testDateConstructor2argsException2() {
        org.python.types.Int num = org.python.types.Int.getInt(1);
        org.python.Object[] args = {num, num};
        Exception exception = assertThrows(org.python.exceptions.TypeError.class, () -> new Date(args, Collections.emptyMap()));
        assertEquals("function missing required argument 'day' (pos 3)", exception.getMessage());
    }

    @Test
    public void testDateConstructor1argsException() {
        org.python.types.Int num = org.python.types.Int.getInt(1);
        org.python.Object[] args = {};
        Map<String, org.python.Object> kwargs = new HashMap<String, org.python.Object>() {
            {
                put("month", num);
            }
        };
        Exception exception = assertThrows(org.python.exceptions.TypeError.class, () -> new Date(args, kwargs));
        assertEquals("function missing required argument 'year' (pos 1)", exception.getMessage());
    }

    @Test
    public void testComparisonOperators() {
        Date d2018_8_1 = createDate(2018, 8, 1);
        Date d2018_12_31 = createDate(2018, 12, 31);
        Date d2021_7_21 = createDate(2021, 7, 21);
        Date d2021_9_14 = createDate(2021, 9, 14);
        Date d2021_9_15 = createDate(2021, 9, 15);
        Date d2022_4_28 = createDate(2022, 4, 28);

        Date d2021_9_14_same = createDate(2021, 9, 14);

        // __lt__
        assertTrue(((org.python.types.Bool) d2021_9_14.__lt__(d2022_4_28)).value);
        assertTrue(((org.python.types.Bool) d2021_9_14.__lt__(d2021_9_15)).value);
        assertFalse(((org.python.types.Bool) d2021_9_14.__lt__(d2018_8_1)).value);
        assertFalse(((org.python.types.Bool) d2021_9_14.__lt__(d2018_12_31)).value);
        assertFalse(((org.python.types.Bool) d2021_9_14.__lt__(d2021_7_21)).value);
        assertFalse(((org.python.types.Bool) d2021_9_14.__lt__(d2021_9_14_same)).value);
        assertEquals(org.python.types.NotImplementedType.NOT_IMPLEMENTED, d2021_9_14.__lt__(org.python.types.Int.getInt(1)));

        // __le__
        assertTrue(((org.python.types.Bool) d2021_9_14.__le__(d2022_4_28)).value);
        assertTrue(((org.python.types.Bool) d2021_9_14.__le__(d2021_9_15)).value);
        assertFalse(((org.python.types.Bool) d2021_9_14.__le__(d2018_8_1)).value);
        assertFalse(((org.python.types.Bool) d2021_9_14.__le__(d2018_12_31)).value);
        assertFalse(((org.python.types.Bool) d2021_9_14.__le__(d2021_7_21)).value);
        assertTrue(((org.python.types.Bool) d2021_9_14.__le__(d2021_9_14_same)).value);
        assertEquals(org.python.types.NotImplementedType.NOT_IMPLEMENTED, d2021_9_14.__le__(org.python.types.Int.getInt(1)));

        // __eq__
        assertFalse(((org.python.types.Bool) d2021_9_14.__eq__(d2022_4_28)).value);
        assertFalse(((org.python.types.Bool) d2021_9_14.__eq__(d2021_9_15)).value);
        assertFalse(((org.python.types.Bool) d2021_9_14.__eq__(d2018_8_1)).value);
        assertFalse(((org.python.types.Bool) d2021_9_14.__eq__(d2018_12_31)).value);
        assertFalse(((org.python.types.Bool) d2021_9_14.__eq__(d2021_7_21)).value);
        assertTrue(((org.python.types.Bool) d2021_9_14.__eq__(d2021_9_14_same)).value);
        assertEquals(org.python.types.NotImplementedType.NOT_IMPLEMENTED, d2021_9_14.__eq__(org.python.types.Int.getInt(1)));

        // __ne__
        assertTrue(((org.python.types.Bool) d2021_9_14.__ne__(d2022_4_28)).value);
        assertTrue(((org.python.types.Bool) d2021_9_14.__ne__(d2021_9_15)).value);
        assertTrue(((org.python.types.Bool) d2021_9_14.__ne__(d2018_8_1)).value);
        assertTrue(((org.python.types.Bool) d2021_9_14.__ne__(d2018_12_31)).value);
        assertTrue(((org.python.types.Bool) d2021_9_14.__ne__(d2021_7_21)).value);
        assertFalse(((org.python.types.Bool) d2021_9_14.__ne__(d2021_9_14_same)).value);
        assertEquals(org.python.types.NotImplementedType.NOT_IMPLEMENTED, d2021_9_14.__ne__(org.python.types.Int.getInt(1)));

        // __ge__
        assertFalse(((org.python.types.Bool) d2021_9_14.__ge__(d2022_4_28)).value);
        assertFalse(((org.python.types.Bool) d2021_9_14.__ge__(d2021_9_15)).value);
        assertTrue(((org.python.types.Bool) d2021_9_14.__ge__(d2018_8_1)).value);
        assertTrue(((org.python.types.Bool) d2021_9_14.__ge__(d2018_12_31)).value);
        assertTrue(((org.python.types.Bool) d2021_9_14.__ge__(d2021_7_21)).value);
        assertTrue(((org.python.types.Bool) d2021_9_14.__ge__(d2021_9_14_same)).value);
        assertEquals(org.python.types.NotImplementedType.NOT_IMPLEMENTED, d2021_9_14.__ge__(org.python.types.Int.getInt(1)));

        // __gt__
        assertFalse(((org.python.types.Bool) d2021_9_14.__gt__(d2022_4_28)).value);
        assertFalse(((org.python.types.Bool) d2021_9_14.__gt__(d2021_9_15)).value);
        assertTrue(((org.python.types.Bool) d2021_9_14.__gt__(d2018_8_1)).value);
        assertTrue(((org.python.types.Bool) d2021_9_14.__gt__(d2018_12_31)).value);
        assertTrue(((org.python.types.Bool) d2021_9_14.__gt__(d2021_7_21)).value);
        assertFalse(((org.python.types.Bool) d2021_9_14.__gt__(d2021_9_14_same)).value);
        assertEquals(org.python.types.NotImplementedType.NOT_IMPLEMENTED, d2021_9_14.__gt__(org.python.types.Int.getInt(1)));
    }

    @Test
    public void testIsoformat() {
        Date d1_1_1 = createDate(1, 1, 1);
        Date d2021_9_15 = createDate(2021, 9, 15);
        assertEquals("0001-01-01", ((org.python.types.Str) d1_1_1.isoformat()).value);
        assertEquals("2021-09-15", ((org.python.types.Str) d2021_9_15.isoformat()).value);
    }

    @Test
    public void testFromIsoformat() {
        Date d1_1_1 = createDate(1, 1, 1);
        org.python.types.Str s1_1_1 = new org.python.types.Str("0001-01-01");
        Date d2021_9_15 = createDate(2021, 9, 15);
        org.python.types.Str s2021_9_15 = new org.python.types.Str("2021-09-15");
        assertTrue(((org.python.types.Bool) d1_1_1.__eq__(Date.fromisoformat(s1_1_1))).value);
        assertTrue(((org.python.types.Bool) d2021_9_15.__eq__(Date.fromisoformat(s2021_9_15))).value);
    }

    @Test
    public void testFromIsoformatBadString() {
        org.python.types.Str bad1 = new org.python.types.Str("1-1-1");
        org.python.types.Str bad2 = new org.python.types.Str("20b1-12-01");
        org.python.types.Str bad3 = new org.python.types.Str("sdfgsaldkuhad");
        org.python.types.Str bad4 = new org.python.types.Str("viek-eg-xb");
        org.python.types.Str bad5 = new org.python.types.Str("");
        org.python.types.Str bad6 = new org.python.types.Str("202\n-12-01");
        org.python.types.Str bad7 = new org.python.types.Str("-200-12-01");
        Exception exception = assertThrows(org.python.exceptions.ValueError.class, () -> Date.fromisoformat(bad1));
        assertEquals("Invalid isoformat string: '" + bad1 + "'", exception.getMessage());
        exception = assertThrows(org.python.exceptions.ValueError.class, () -> Date.fromisoformat(bad2));
        assertEquals("Invalid isoformat string: '" + bad2 + "'", exception.getMessage());
        exception = assertThrows(org.python.exceptions.ValueError.class, () -> Date.fromisoformat(bad3));
        assertEquals("Invalid isoformat string: '" + bad3 + "'", exception.getMessage());
        exception = assertThrows(org.python.exceptions.ValueError.class, () -> Date.fromisoformat(bad4));
        assertEquals("Invalid isoformat string: '" + bad4 + "'", exception.getMessage());
        exception = assertThrows(org.python.exceptions.ValueError.class, () -> Date.fromisoformat(bad5));
        assertEquals("Invalid isoformat string: '" + bad5 + "'", exception.getMessage());
        exception = assertThrows(org.python.exceptions.ValueError.class, () -> Date.fromisoformat(bad6));
        assertEquals("Invalid isoformat string: '" + bad6 + "'", exception.getMessage());
        exception = assertThrows(org.python.exceptions.ValueError.class, () -> Date.fromisoformat(bad7));
        assertEquals("Invalid isoformat string: '" + bad7 + "'", exception.getMessage());
    }

    @Test
    public void testIsoformatInverse() {
        org.python.types.Str s1_2_3 = new org.python.types.Str("0001-02-03");
        org.python.types.Str s2020_6_12 = new org.python.types.Str("2020-06-12");
        assertEquals(s1_2_3.value, ((org.python.types.Str) ((Date) Date.fromisoformat(s1_2_3)).isoformat()).value);
        assertEquals(s2020_6_12.value, ((org.python.types.Str) ((Date) Date.fromisoformat(s2020_6_12)).isoformat()).value);
    }
}
