import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertEquals(d, Date.max);
        //assertTrue(d.__eq__(Date.max));
    }

    @Test
    public void testMin() {
        Date d = createDate(python.datetime.MINYEAR.value, 1, 1);
        assertEquals(d, Date.min);
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
        Map<String, org.python.Object> kwargs = new HashMap<String, org.python.Object>() {{
            put("year", num);
        }};
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
        Map<String, org.python.Object> kwargs = new HashMap<String, org.python.Object>() {{
            put("month", num);
        }};
        Exception exception = assertThrows(org.python.exceptions.TypeError.class, () -> new Date(args, kwargs));
        assertEquals("function missing required argument 'year' (pos 1)", exception.getMessage());
    }
}
