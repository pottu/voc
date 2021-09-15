import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.python.Object;
import org.python.exceptions.ValueError;

import org.python.stdlib.datetime.Date;
import org.python.stdlib.datetime.DateTime;
import org.python.types.Int;

import java.time.LocalDateTime;
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
        assertThrows(Exception.class,()-> new DateTime(args1,new HashMap<>()));

        // Invalid Month
        org.python.types.Int year2 = org.python.types.Int.getInt(10);
        org.python.types.Int month2 = org.python.types.Int.getInt(15);
        org.python.types.Int day2= org.python.types.Int.getInt(25);
        org.python.Object[] args2 = {year2, month2, day2};
        assertThrows(ValueError.class,()-> new DateTime(args2,new HashMap<>()));

        // Invalid Day
        org.python.types.Int year3= org.python.types.Int.getInt(10);
        org.python.types.Int month3 = org.python.types.Int.getInt(10);
        org.python.types.Int day3= org.python.types.Int.getInt(50);
        org.python.Object[] args3 = {year3, month3, day3};
        assertThrows(ValueError.class,()-> new DateTime(args3,new HashMap<>()));
    }
    @Test
    public void TestConstructor3(){
        // Want to Fail, kwargs not null

        // Invalid Year
        org.python.types.Int month1 = org.python.types.Int.getInt(10);
        org.python.types.Int day1= org.python.types.Int.getInt(25);
        org.python.Object[] args1 = {month1, day1};
        Map<String, Object> kwargs= new HashMap<String,org.python.Object>() {
            {
                put("year", org.python.types.Int.getInt(10));
            }
        };
        assertThrows(org.python.exceptions.SyntaxError.class, () -> new DateTime(args1, kwargs));

        // Invalid Month
        org.python.types.Int year2 = org.python.types.Int.getInt(10);
        org.python.types.Int day2= org.python.types.Int.getInt(25);
        org.python.Object[] args2 = {year2, day2};
        Map<String, Object> kwargs2= new HashMap<String,org.python.Object>() {
            {
                put("month", org.python.types.Int.getInt(10));
            }
        };
        assertThrows(org.python.exceptions.SyntaxError.class, () -> new DateTime(args2, kwargs2));
    }
    @Test
    public void TestConstructor4(){
        // Want to Success, kwargs not null

        // All 3
        org.python.Object[] args = {};
        Map<String, Object> kwargs= new HashMap<String,org.python.Object>() {
            {
                put("day", org.python.types.Int.getInt(10));
                put("month", org.python.types.Int.getInt(10));
                put("year", org.python.types.Int.getInt(10));
            }
        };
        assertDoesNotThrow(() -> new DateTime(args, kwargs));

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
    }
    @Test
    public void TestConstructor5(){
        // Want to fail, Invalid type, kwargs null
        org.python.types.Str year = new org.python.types.Str("hej");
        org.python.types.Int month = org.python.types.Int.getInt(10);
        org.python.types.Int day= org.python.types.Int.getInt(25);
        org.python.Object[] args = {year, month, day};
        assertThrows( org.python.exceptions.TypeError.class,()-> new DateTime(args,new HashMap<>()));
        org.python.types.Int year1 = org.python.types.Int.getInt(1);
        org.python.types.List month1 = new org.python.types.List();
        org.python.types.Int day1= org.python.types.Int.getInt(25);
        org.python.Object[] args2 = {year1, month1, day1};
        assertThrows( org.python.exceptions.TypeError.class,()-> new DateTime(args2,new HashMap<>()));

        // Want to fail, Invalid type, kwargs not null
        org.python.Object[] args3 = {};
        Map<String, Object> kwargs= new HashMap<String,org.python.Object>() {
            {
                put("day", org.python.types.Int.getInt(10));
                put("month", org.python.types.Int.getInt(10));
                put("year", new org.python.types.Str("hej"));
            }
        };
        assertThrows(org.python.exceptions.TypeError.class,() -> new DateTime(args3, kwargs));
    }
    @Test
    public void TestConstructor6(){
        // Want to Fail, Not Enough args
        org.python.types.Int year = org.python.types.Int.getInt(10);
        org.python.types.Int month = org.python.types.Int.getInt(10);
        org.python.Object[] args = {year, month};
        assertThrows(org.python.exceptions.TypeError.class,()-> new DateTime(args,new HashMap<>()));
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
        assertDoesNotThrow(()-> new DateTime(args,new HashMap<>()));
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

}
