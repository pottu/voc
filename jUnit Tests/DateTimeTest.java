import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.python.Object;
import org.python.exceptions.TypeError;
import org.python.exceptions.ValueError;
import org.python.exceptions.SyntaxError;

import org.python.stdlib.datetime.Date;
import org.python.stdlib.datetime.DateTime;
import org.python.types.Int;

import java.util.HashMap;
import java.util.Map;


public class DateTimeTest {
    @Test
    public void TestConstructor1(){
        // Want to Success, valid args, kwargs as null
        org.python.types.Int year = org.python.types.Int.getInt(10);
        org.python.types.Int month = org.python.types.Int.getInt(10);
        org.python.types.Int day= org.python.types.Int.getInt(25);
        org.python.Object[] args = {year, month, day};
        assertDoesNotThrow(()-> new DateTime(args,new HashMap<>()));
    }
    @Test
    public void TestConstructor2(){
        // Want to Fail, invalid args but right type, kwargs as null

        // Invalid Year
        org.python.types.Int year1 = org.python.types.Int.getInt(0);
        org.python.types.Int month1 = org.python.types.Int.getInt(10);
        org.python.types.Int day1= org.python.types.Int.getInt(25);
        org.python.Object[] args1 = {year1, month1, day1};
        Exception ex = assertThrows(ValueError.class,()-> new DateTime(args1,new HashMap<>()));
        assertEquals("year 0 is out of range", ex.getMessage());

        // Invalid Month
        org.python.types.Int year2 = org.python.types.Int.getInt(10);
        org.python.types.Int month2 = org.python.types.Int.getInt(15);
        org.python.types.Int day2= org.python.types.Int.getInt(25);
        org.python.Object[] args2 = {year2, month2, day2};
        ex = assertThrows(ValueError.class,()-> new DateTime(args2,new HashMap<>()));
        assertEquals("month must be in 1..12", ex.getMessage());

        // Invalid Day
        org.python.types.Int year3= org.python.types.Int.getInt(2021);
        org.python.types.Int month3 = org.python.types.Int.getInt(2);
        org.python.types.Int day3= org.python.types.Int.getInt(29);
        org.python.Object[] args3 = {year3, month3, day3};
        ex = assertThrows(ValueError.class,()-> new DateTime(args3,new HashMap<>()));
        assertEquals( "day is out of range for month", ex.getMessage());

        // Invalid Args
        org.python.Object[] args4 = {};
        ex = assertThrows(TypeError.class,()-> new DateTime(args4,new HashMap<>()));
        assertEquals( "function missing required argument 'year' (pos 1)", ex.getMessage());

        org.python.Object[] args5 = {year3};
        ex = assertThrows(TypeError.class,()-> new DateTime(args5,new HashMap<>()));
        assertEquals( "function missing required argument 'month' (pos 2)", ex.getMessage());

        org.python.Object[] args6 = {year3,month3};
        ex = assertThrows(TypeError.class,()-> new DateTime(args6,new HashMap<>()));
        assertEquals( "function missing required argument 'day' (pos 3)", ex.getMessage());
    }
    @Test
    public void TestConstructor3(){
        // Want to Fail, kwargs not null

        // Invalid Year
        org.python.types.Int month1 = org.python.types.Int.getInt(10);
        org.python.types.Int day1= org.python.types.Int.getInt(25);
        org.python.Object[] args1 = {month1, day1};
        Map<String, Object> kwargs= new HashMap<>() {
            {
                put("year", org.python.types.Int.getInt(10));
            }
        };
        assertThrows(SyntaxError.class, () -> new DateTime(args1, kwargs));

        // Invalid Month
        org.python.types.Int year2 = org.python.types.Int.getInt(10);
        org.python.types.Int day2= org.python.types.Int.getInt(25);
        org.python.Object[] args2 = {year2, day2};
        Map<String, Object> kwargs2= new HashMap<String,org.python.Object>() {
            {
                put("month", org.python.types.Int.getInt(10));
            }
        };
        assertThrows(SyntaxError.class, () -> new DateTime(args2, kwargs2));
    }
    @Test
    public void TestConstructor4(){
        // Want to Success, kwargs not null

        // All 3
        org.python.Object[] args = {};
        Map<String, Object> kwargs= new HashMap<String,org.python.Object>() {
            {
                put("day", org.python.types.Int.getInt(1));
                put("month", org.python.types.Int.getInt(2));
                put("year", org.python.types.Int.getInt(3));
            }
        };
        DateTime d = new DateTime(args, kwargs);
        assertDoesNotThrow(() -> d);
        assertEquals("0003-02-01 00:00:00", d.__str__().value);

        // Last 2
        org.python.types.Int year= org.python.types.Int.getInt(10);
        org.python.Object[] args2 = {year};
        Map<String, Object> kwargs2= new HashMap<String,org.python.Object>() {
            {
                put("day", org.python.types.Int.getInt(10));
                put("month", org.python.types.Int.getInt(10));

            }
        };
        assertDoesNotThrow(() -> new DateTime(args2, kwargs2));

        // Last 1
        org.python.types.Int year2= org.python.types.Int.getInt(10);
        org.python.types.Int month2= org.python.types.Int.getInt(10);
        org.python.Object[] args3 = {year2,month2};
        Map<String, Object> kwargs3= new HashMap<String,org.python.Object>() {
            {
                put("day", org.python.types.Int.getInt(10));


            }
        };
        assertDoesNotThrow(() -> new DateTime(args3, kwargs3));

        // With Extra
        org.python.types.Int year3= org.python.types.Int.getInt(10);
        org.python.types.Int month3= org.python.types.Int.getInt(10);
        org.python.types.Int day3= org.python.types.Int.getInt(10);
        org.python.Object[] args4 = {year3,month3,day3};
        Map<String, Object> kwargs4= new HashMap<String,org.python.Object>() {
            {
                put("hour", org.python.types.Int.getInt(10));
                put("minute", org.python.types.Int.getInt(40));
                put("second", org.python.types.Int.getInt(30));
                put("microsecond", org.python.types.Int.getInt(1110));
            }
        };
        DateTime d1 = new DateTime(args4, kwargs4);
        assertDoesNotThrow(() -> d1);
        assertEquals("0010-10-10 10:40:30.001110",d1.__str__().value);
    }
    @Test
    public void TestConstructor5(){
        // Want to fail, Invalid type, kwargs null
        org.python.types.Str year = new org.python.types.Str("hej");
        org.python.types.Int month = org.python.types.Int.getInt(10);
        org.python.types.Int day= org.python.types.Int.getInt(25);
        org.python.Object[] args = {year, month, day};
        assertThrows(TypeError.class,()-> new DateTime(args,new HashMap<>()));

        org.python.types.Int year1 = org.python.types.Int.getInt(1);
        org.python.types.List month1 = new org.python.types.List();
        org.python.types.Int day1= org.python.types.Int.getInt(25);
        org.python.Object[] args2 = {year1, month1, day1};
        assertThrows(TypeError.class,()-> new DateTime(args2,new HashMap<>()));

        // Want to fail, Invalid type, kwargs not null
        org.python.Object[] args3 = {};
        Map<String, Object> kwargs= new HashMap<String,org.python.Object>() {
            {
                put("day", org.python.types.Int.getInt(10));
                put("month", org.python.types.Int.getInt(10));
                put("year", new org.python.types.Str("hej"));
            }
        };
        assertThrows(TypeError.class,() -> new DateTime(args3, kwargs));
    }
    @Test
    public void TestConstructor6(){
        // Want to Fail, Not Enough args
        org.python.types.Int year = org.python.types.Int.getInt(10);
        org.python.types.Int month = org.python.types.Int.getInt(10);
        org.python.Object[] args = {year, month};
        assertThrows(TypeError.class,()-> new DateTime(args,new HashMap<>()));
    }
    @Test
    public void TestConstructor7(){
        // Want to Success, add all values
        org.python.types.Int year = org.python.types.Int.getInt(10);
        org.python.types.Int month = org.python.types.Int.getInt(10);
        org.python.types.Int day = org.python.types.Int.getInt(10);
        org.python.types.Int hour = org.python.types.Int.getInt(10);
        org.python.types.Int minute = org.python.types.Int.getInt(10);
        org.python.types.Int second = org.python.types.Int.getInt(10);
        org.python.types.Int microsecond = org.python.types.Int.getInt(999999);
        org.python.Object[] args = {year, month,day,hour,minute,second,microsecond};
        DateTime d = new DateTime(args,new HashMap<>());
        assertDoesNotThrow(()-> d);
        assertEquals("0010-10-10 10:10:10.999999",d.__str__().value);
    }
    @Test
    public void TestConstructor8(){
        // Want to Fail, add all values
        org.python.types.Int year = org.python.types.Int.getInt(10);
        org.python.types.Int month = org.python.types.Int.getInt(10);
        org.python.types.Int day = org.python.types.Int.getInt(10);
        org.python.types.Int hour = org.python.types.Int.getInt(30);
        org.python.types.Int minute = org.python.types.Int.getInt(10);
        org.python.types.Int second = org.python.types.Int.getInt(10);
        org.python.types.Int microsecond = org.python.types.Int.getInt(999999);
        org.python.Object[] args = {year, month,day,hour,minute,second,microsecond};
        assertThrows(ValueError.class,()-> new DateTime(args,new HashMap<>()));
    }
    @Test
    public void TestConstructor9(){
        // Want to fail, add all values
        org.python.types.Int year = org.python.types.Int.getInt(10);
        org.python.types.Int month = org.python.types.Int.getInt(10);
        org.python.types.Int day = org.python.types.Int.getInt(10);
        org.python.types.Int hour = org.python.types.Int.getInt(10);
        org.python.types.Int minute = org.python.types.Int.getInt(-1);
        org.python.types.Int second = org.python.types.Int.getInt(10);
        org.python.types.Int microsecond = org.python.types.Int.getInt(999999);
        org.python.Object[] args = {year, month,day,hour,minute,second,microsecond};
        assertThrows(ValueError.class,()-> new DateTime(args,new HashMap<>()));
    }
    @Test
    public void TestConstructor10(){
        // Want to fail, add all values
        org.python.types.Int year = org.python.types.Int.getInt(10);
        org.python.types.Int month = org.python.types.Int.getInt(10);
        org.python.types.Int day = org.python.types.Int.getInt(10);
        org.python.types.Int hour = org.python.types.Int.getInt(10);
        org.python.types.Int minute = org.python.types.Int.getInt(3);
        org.python.types.Int second = org.python.types.Int.getInt(70);
        org.python.types.Int microsecond = org.python.types.Int.getInt(999999);
        org.python.Object[] args = {year, month,day,hour,minute,second,microsecond};
        assertThrows(ValueError.class,()-> new DateTime(args,new HashMap<>()));
    }
    @Test
    public void TestConstructor11(){
        // Want to fail, add all values
        org.python.types.Int year = org.python.types.Int.getInt(10);
        org.python.types.Int month = org.python.types.Int.getInt(10);
        org.python.types.Int day = org.python.types.Int.getInt(10);
        org.python.types.Int hour = org.python.types.Int.getInt(10);
        org.python.types.Int minute = org.python.types.Int.getInt(3);
        org.python.types.Int second = org.python.types.Int.getInt(50);
        org.python.types.Int microsecond = org.python.types.Int.getInt(9999999);
        org.python.Object[] args = {year, month,day,hour,minute,second,microsecond};
        assertThrows(ValueError.class,()-> new DateTime(args,new HashMap<>()));
    }
    @Test
    public void Test_Str() {
        org.python.types.Int year = org.python.types.Int.getInt(10);
        org.python.types.Int month = org.python.types.Int.getInt(10);
        org.python.types.Int day = org.python.types.Int.getInt(10);
        org.python.Object[] args = {year, month,day};
        DateTime d = new DateTime(args,new HashMap<>());
        assertEquals("0010-10-10 00:00:00",d.__str__().value);

        org.python.types.Int year1 = org.python.types.Int.getInt(10);
        org.python.types.Int month1 = org.python.types.Int.getInt(10);
        org.python.types.Int day1 = org.python.types.Int.getInt(10);
        org.python.types.Int hour1 = org.python.types.Int.getInt(10);
        org.python.types.Int minute1 = org.python.types.Int.getInt(10);
        org.python.types.Int second1 = org.python.types.Int.getInt(10);
        org.python.types.Int microsecond1 = org.python.types.Int.getInt(999999);
        org.python.Object[] args1 = {year1, month1,day1,hour1,minute1,second1,microsecond1};
        DateTime d1 = new DateTime(args1,new HashMap<>());
        assertEquals("0010-10-10 10:10:10.999999",d1.__str__().value);
    }

    /* @Test Does not work ATM, NEED DATE IMPLEMENTATION
    public void Test_Date() {
        org.python.types.Int year = org.python.types.Int.getInt(10);
        org.python.types.Int month = org.python.types.Int.getInt(10);
        org.python.types.Int day = org.python.types.Int.getInt(10);
        org.python.Object[] args = {year, month,day};
        DateTime d = new DateTime(args,new HashMap<>());
        assertDoesNotThrow(()-> (Date) d.date());
    }*/

    @Test
    public void Test_Today() {
        org.python.types.Int year = org.python.types.Int.getInt(10);
        org.python.types.Int month = org.python.types.Int.getInt(10);
        org.python.types.Int day = org.python.types.Int.getInt(10);
        org.python.Object[] args = {year, month,day};
        DateTime d = new DateTime(args,new HashMap<>());
        assertDoesNotThrow(()->d.today());
    }
    @Test
    public void Test_Weekday() {
        org.python.types.Int year = org.python.types.Int.getInt(2021);
        org.python.types.Int month = org.python.types.Int.getInt(9);
        org.python.types.Int day = org.python.types.Int.getInt(15);
        org.python.Object[] args = {year, month,day};
        DateTime d = new DateTime(args,new HashMap<>());
        assertDoesNotThrow(()->d.weekday());
        assertEquals(2,((Int) d.weekday()).value);
    }
    @Test
    public void Test_ISOWeekday() {
        org.python.types.Int year = org.python.types.Int.getInt(2021);
        org.python.types.Int month = org.python.types.Int.getInt(9);
        org.python.types.Int day = org.python.types.Int.getInt(15);
        org.python.Object[] args = {year, month,day};
        DateTime d = new DateTime(args,new HashMap<>());
        assertDoesNotThrow(()->d.isoweekday());
        assertEquals(3,((Int) d.isoweekday()).value);

        org.python.types.Int year1 = org.python.types.Int.getInt(2021);
        org.python.types.Int month1 = org.python.types.Int.getInt(9);
        org.python.types.Int day1 = org.python.types.Int.getInt(19);
        org.python.Object[] args1 = {year1, month1,day1};
        DateTime d1 = new DateTime(args1,new HashMap<>());
        assertDoesNotThrow(()->d1.isoweekday());
        assertEquals(7,((Int) d1.isoweekday()).value);
    }
    @Test
    public void Test_fromordinal() {
        org.python.types.Int year = org.python.types.Int.getInt(4);
        org.python.types.Int month = org.python.types.Int.getInt(5);
        org.python.types.Int day = org.python.types.Int.getInt(15);
        org.python.types.Int ordinal = org.python.types.Int.getInt(1231);
        org.python.Object[] args = {year, month,day};
        DateTime d = new DateTime(args,new HashMap<>());
        assertEquals(d.__str__(),DateTime.fromordinal(ordinal).__str__());

        org.python.types.Int year1 = org.python.types.Int.getInt(34);
        org.python.types.Int month1 = org.python.types.Int.getInt(12);
        org.python.types.Int day1 = org.python.types.Int.getInt(24);
        org.python.types.Int ordinal1 = org.python.types.Int.getInt(12411);
        org.python.Object[] args1 = {year1, month1,day1};
        DateTime d1 = new DateTime(args1,new HashMap<>());
        assertEquals(d1.__str__(),DateTime.fromordinal(ordinal1 ).__str__());

        org.python.types.Int year2 = org.python.types.Int.getInt(3399);
        org.python.types.Int month2 = org.python.types.Int.getInt(2);
        org.python.types.Int day2 = org.python.types.Int.getInt(8);
        org.python.types.Int ordinal2 = org.python.types.Int.getInt(1241133);
        org.python.Object[] args2 = {year2, month2,day2};
        DateTime d2 = new DateTime(args2,new HashMap<>());
        assertEquals(d2.__str__(),DateTime.fromordinal(ordinal2).__str__());

        org.python.types.Int year3 = org.python.types.Int.getInt(1);
        org.python.types.Int month3 = org.python.types.Int.getInt(1);
        org.python.types.Int day3 = org.python.types.Int.getInt(1);
        org.python.types.Int ordinal3 = org.python.types.Int.getInt(1);
        org.python.Object[] args3 = {year3, month3,day3};
        DateTime d3 = new DateTime(args3,new HashMap<>());
        assertEquals(d3.__str__(),DateTime.fromordinal(ordinal3).__str__());

        org.python.types.Int year4 = org.python.types.Int.getInt(34);
        org.python.types.Int month4 = org.python.types.Int.getInt(12);
        org.python.types.Int day4 = org.python.types.Int.getInt(26);
        org.python.types.Int ordinal4 = org.python.types.Int.getInt(12413);
        org.python.Object[] args4 = {year4, month4,day4};
        DateTime d4 = new DateTime(args4,new HashMap<>());
        assertEquals(d4.__str__(),DateTime.fromordinal(ordinal4).__str__());

        org.python.types.Int year5 = org.python.types.Int.getInt(9999);
        org.python.types.Int month5 = org.python.types.Int.getInt(12);
        org.python.types.Int day5 = org.python.types.Int.getInt(31);
        org.python.types.Int ordinal5 = org.python.types.Int.getInt(3652059);
        org.python.Object[] args5 = {year5, month5,day5};
        DateTime d5 = new DateTime(args5,new HashMap<>());
        assertEquals(d5.__str__(),DateTime.fromordinal(ordinal5).__str__());
    }

}
