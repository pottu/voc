package org.python.stdlib.datetime;

import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class DateTime extends org.python.types.Object {
    private final int YEAR_INDEX = 0;
    private final int MONTH_INDEX = 1;
    private final int DAY_INDEX = 2;
    private final int HOUR_INDEX = 3;
    private final int MINUTE_INDEX = 4;
    private final int SECOND_INDEX = 5;
    private final int MICROSECOND_INDEX = 6;
    private final int[] DAYS_PER_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private final int LEAP_YEAR = 29;
    private final int MIN_YEAR = 1;
    private final int MAX_YEAR = 9999;

    private final Long[] timeUnits = {0L, 0L, 0L, 0L, 0L, 0L, 0L};

    @org.python.Attribute
    public final org.python.Object year;

    @org.python.Attribute
    public final org.python.Object month;

    @org.python.Attribute
    public final org.python.Object day;

    @org.python.Attribute
    public final org.python.Object hour;

    @org.python.Attribute
    public final org.python.Object minute;

    @org.python.Attribute
    public final org.python.Object second;

    @org.python.Attribute
    public final org.python.Object microsecond;

    @org.python.Attribute
    public static final org.python.Object min = __min__();

    @org.python.Attribute
    public static final org.python.Object max = __max__();

    @org.python.Method(__doc__ = "datetime(year, month, day[, hour[, minute[, second[, microsecond[,tzinfo]]]]])\n" + "The year, month and day arguments are required. tzinfo may be None, or an\n" + "instance of a tzinfo subclass. The remaining arguments may be ints.\n")
    public DateTime(org.python.Object[] args, java.util.Map<java.lang.String, org.python.Object> kwargs) {
        super();
        String[] keys = {"year", "month", "day", "hour", "minute", "second", "microsecond"};
        boolean kwargsIsUsed = false;
        int keyIndex = 0;
        int argIndex = 0;

        for (String key : keys) {
            if (kwargs.get(key) != null) {
                if (!(kwargs.get(key) instanceof org.python.types.Int)) {
                    throw new org.python.exceptions.TypeError("an integer is required (got type" + kwargs.get(key).typeName() + ")");
                }
                this.timeUnits[keyIndex] = ((org.python.types.Int) kwargs.get(key)).value;
                kwargsIsUsed = true;
            } else if (args.length > argIndex) {
                if (kwargsIsUsed) {
                    throw new org.python.exceptions.SyntaxError("positional argument follows keyword argument");
                }
                if (!(args[argIndex] instanceof org.python.types.Int)) {
                    throw new org.python.exceptions.TypeError("an integer is required (got type" + args[argIndex].typeName() + ")");
                }
                this.timeUnits[keyIndex] = ((org.python.types.Int) args[argIndex]).value;
                argIndex++;
            } else if (keyIndex < 3) {
                throw new org.python.exceptions.TypeError("function missing required argument '" + keys[keyIndex] + "' (pos " + (keyIndex + 1) + ")");
            }
            keyIndex++;
        }
        GregorianCalendar c = new GregorianCalendar();
        if (c.isLeapYear(this.timeUnits[YEAR_INDEX].intValue())) {
            DAYS_PER_MONTH[1] = LEAP_YEAR;
        }
        if (this.timeUnits[YEAR_INDEX] < MIN_YEAR || this.timeUnits[YEAR_INDEX] > MAX_YEAR) {
            throw new org.python.exceptions.ValueError("year " + this.timeUnits[YEAR_INDEX] + " is out of range");
        }

        if (this.timeUnits[MONTH_INDEX] < 1 || this.timeUnits[MONTH_INDEX] > 12) {
            throw new org.python.exceptions.ValueError("month must be in 1..12");
        }
        if (this.timeUnits[DAY_INDEX] < 1 || this.timeUnits[DAY_INDEX] > DAYS_PER_MONTH[this.timeUnits[MONTH_INDEX].intValue() - 1]) {
            throw new org.python.exceptions.ValueError("day is out of range for month");
        }

        if (this.timeUnits[HOUR_INDEX] < 0 || this.timeUnits[HOUR_INDEX] > 23) {
            throw new org.python.exceptions.ValueError("hour must be in 0..23");
        }

        if (this.timeUnits[MINUTE_INDEX] < 0 || this.timeUnits[MINUTE_INDEX] > 59) {
            throw new org.python.exceptions.ValueError("minute must be in 0..59");
        }

        if (this.timeUnits[SECOND_INDEX] < 0 || this.timeUnits[SECOND_INDEX] > 59) {
            throw new org.python.exceptions.ValueError("second must be in 0..59");
        }

        if (this.timeUnits[MICROSECOND_INDEX] < 0 || this.timeUnits[MICROSECOND_INDEX] >= 1000000) {
            throw new org.python.exceptions.ValueError("microsecond must be in 0..999999    ");
        }

        this.year = __year__();
        this.month = __month__();
        this.day = __day__();
        this.hour = __hour__();
        this.minute = __minute__();
        this.second = __second__();
        this.microsecond = __microsecond__();
    }

    public org.python.types.Str __str__() {
        String year = Long.toString(this.timeUnits[YEAR_INDEX]);
        while (year.length() < 4) {
            year = "0" + year;
        }

        String month = Long.toString(this.timeUnits[MONTH_INDEX]);
        while (month.length() < 2) {
            month = "0" + month;
        }
        String day = Long.toString(this.timeUnits[DAY_INDEX]);
        while (day.length() < 2) {
            day = "0" + day;
        }

        String hour = this.timeUnits[HOUR_INDEX] != 0 ? Long.toString(this.timeUnits[HOUR_INDEX]) : "00";
        while (hour.length() < 2) {
            hour = "0" + hour;
        }

        String minute = this.timeUnits[MINUTE_INDEX] != 0 ? Long.toString(this.timeUnits[MINUTE_INDEX]) : "00";
        while (minute.length() < 2) {
            minute = "0" + minute;
        }
        String second = this.timeUnits[SECOND_INDEX] != 0 ? Long.toString(this.timeUnits[SECOND_INDEX]) : "00";
        while (second.length() < 2) {
            second = "0" + second;
        }
        String microsecond = this.timeUnits[MICROSECOND_INDEX] != 0 ? Long.toString(this.timeUnits[MICROSECOND_INDEX]) : "";
        while (microsecond.length() < 6 && microsecond.length() != 0) {
            microsecond = "0" + microsecond;
        }
        String returnStr = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;

        returnStr += microsecond.length() > 0 ? "." + microsecond : "";
        return new org.python.types.Str(returnStr);
    }

    @org.python.Method(__doc__ = "date(year, month, day) --> date object")
    public org.python.Object date() {
        org.python.Object[] args = {org.python.types.Int.getInt(this.timeUnits[YEAR_INDEX]), org.python.types.Int.getInt(this.timeUnits[MONTH_INDEX]),
            org.python.types.Int.getInt(this.timeUnits[DAY_INDEX])};
        return new Date(args, Collections.emptyMap());
    }

    @org.python.Method(__doc__ = "Return the current local date.")
    public static org.python.Object today() {
        java.time.LocalDateTime today = java.time.LocalDateTime.now();
        org.python.Object[] args = {org.python.types.Int.getInt(today.getYear()), org.python.types.Int.getInt(today.getMonth().getValue()),
            org.python.types.Int.getInt(today.getDayOfMonth()), org.python.types.Int.getInt(today.getHour()), org.python.types.Int.getInt(today.getMinute()),
            org.python.types.Int.getInt(today.getSecond()), org.python.types.Int.getInt(today.getNano() / 1000)};
        return new DateTime(args, Collections.emptyMap());
    }

    @org.python.Method(__doc__ = "returns year")
    public org.python.types.Int __year__() {
        return org.python.types.Int.getInt(this.timeUnits[YEAR_INDEX]);
    }

    @org.python.Method(__doc__ = "returns month")
    public org.python.types.Int __month__() {
        return org.python.types.Int.getInt(this.timeUnits[MONTH_INDEX]);
    }

    @org.python.Method(__doc__ = "returns day")
    public org.python.types.Int __day__() {
        return org.python.types.Int.getInt(this.timeUnits[DAY_INDEX]);
    }

    @org.python.Method(__doc__ = "returns hour")
    public org.python.types.Int __hour__() {
        return org.python.types.Int.getInt(this.timeUnits[HOUR_INDEX]);
    }

    @org.python.Method(__doc__ = "returns minute")
    public org.python.types.Int __minute__() {
        return org.python.types.Int.getInt(this.timeUnits[MINUTE_INDEX]);
    }

    @org.python.Method(__doc__ = "returns second")
    public org.python.types.Int __second__() {
        return org.python.types.Int.getInt(this.timeUnits[SECOND_INDEX]);
    }

    @org.python.Method(__doc__ = "returns microsecond")
    public org.python.types.Int __microsecond__() {
        return org.python.types.Int.getInt(this.timeUnits[MICROSECOND_INDEX]);
    }

    @org.python.Method(__doc__ = "")
    private static org.python.Object __min__() {
        org.python.types.Int year = org.python.types.Int.getInt(1);
        org.python.types.Int month = org.python.types.Int.getInt(1);
        org.python.types.Int day = org.python.types.Int.getInt(1);

        org.python.Object[] args = {year, month, day};
        return new DateTime(args, Collections.emptyMap());
    }

    @org.python.Method(__doc__ = "")
    private static org.python.Object __max__() {
        org.python.types.Int year = org.python.types.Int.getInt(9999);
        org.python.types.Int month = org.python.types.Int.getInt(12);
        org.python.types.Int day = org.python.types.Int.getInt(31);
        org.python.types.Int hour = org.python.types.Int.getInt(23);
        org.python.types.Int minute = org.python.types.Int.getInt(59);
        org.python.types.Int second = org.python.types.Int.getInt(59);
        org.python.types.Int microsecond = org.python.types.Int.getInt(999999);

        org.python.Object[] args = {year, month, day, hour, minute, second, microsecond};
        return new DateTime(args, Collections.emptyMap());
    }

    @org.python.Method(__doc__ = "Return the day of the week as an integer, where Monday is 0 and Sunday is 6.")
    public org.python.Object weekday() {
        double y = ((org.python.types.Int) this.year).value;
        double m = ((org.python.types.Int) this.month).value;
        double d = ((org.python.types.Int) this.day).value;

        java.util.Date myCalendar = new java.util.GregorianCalendar((int) y, (int) m - 1, (int) d).getTime();
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(myCalendar);
        int day = c.get(java.util.Calendar.DAY_OF_WEEK);
        int[] convertToPython = {6, 0, 1, 2, 3, 4, 5};
        return org.python.types.Int.getInt(convertToPython[day - 1]);
    }

    @org.python.Method(__doc__ = "Return the day of the week as an integer, where Monday is 1 and Sunday is 7.")
    public org.python.Object isoweekday() {
        double y = ((org.python.types.Int) this.year).value;
        double m = ((org.python.types.Int) this.month).value;
        double d = ((org.python.types.Int) this.day).value;

        java.util.Date myCalendar = new java.util.GregorianCalendar((int) y, (int) m - 1, (int) d).getTime();
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(myCalendar);
        int day = c.get(java.util.Calendar.DAY_OF_WEEK);
        int[] convertToPython = {7, 1, 2, 3, 4, 5, 6};
        return org.python.types.Int.getInt(convertToPython[day - 1]);
    }

    @org.python.Method(__doc__ = "Return the datetime corresponding to the proleptic Gregorian ordinal, where January 1 of year 1 has ordinal 1.")
    public static org.python.Object fromordinal(org.python.Object ordinal) {
        long year = 1;
        long month = 1;
        long day = 0;
        long n = ((org.python.types.Int) ordinal).value;
        long tmp = 1;
        while (n > 0) {
            if (year % 4 == 0 && !(year % 100 == 0 && year % 400 != 0)) {
                if (tmp == 367) {
                    year += 1;
                    month = 1;
                    day = 1;
                    tmp = 2;
                    n -= 1;
                    continue;
                }
                if (tmp == 32 || tmp == 61 || tmp == 92 || tmp == 122 || tmp == 153 || tmp == 183 || tmp == 214 || tmp == 245 || tmp == 275 || tmp == 306 || tmp == 336) {
                    month += 1;
                    day = 1;
                } else {
                    day += 1;
                }

            } else {
                if (tmp == 366) {
                    year += 1;
                    month = 1;
                    day = 1;
                    tmp = 2;
                    n -= 1;
                    continue;
                }
                if (tmp == 32 || tmp == 60 || tmp == 91 || tmp == 121 || tmp == 152 || tmp == 182 || tmp == 213 || tmp == 244 || tmp == 274 || tmp == 305 || tmp == 335) {
                    month += 1;
                    day = 1;
                } else {
                    day += 1;
                }
            }
            n -= 1;
            tmp += 1;
        }

        org.python.types.Int n_Year = org.python.types.Int.getInt(year);
        org.python.types.Int n_Month = org.python.types.Int.getInt(month);
        org.python.types.Int n_Day = org.python.types.Int.getInt(day);
        org.python.Object[] args = {n_Year, n_Month, n_Day};
        return new DateTime(args, new HashMap<>());
    }

    @org.python.Method(__doc__ = "Return self < datetime.")
    public org.python.Object __lt__(org.python.Object datetime) {
        if (datetime instanceof DateTime) {
            boolean res = this._lt((DateTime) datetime);
            return org.python.types.Bool.getBool(res);
        } else {
            return org.python.types.NotImplementedType.NOT_IMPLEMENTED;
        }
    }

    @org.python.Method(__doc__ = "Return self <= datetime.")
    public org.python.Object __le__(org.python.Object datetime) {
        if (datetime instanceof DateTime) {
            boolean res = this._lt((DateTime) datetime) || this._eq((DateTime) datetime);
            return org.python.types.Bool.getBool(res);
        } else {
            return org.python.types.NotImplementedType.NOT_IMPLEMENTED;
        }
    }

    @org.python.Method(__doc__ = "Return self == datetime.")
    public org.python.Object __eq__(org.python.Object datetime) {
        if (datetime instanceof DateTime) {
            boolean res = this._eq((DateTime) datetime);
            return org.python.types.Bool.getBool(res);
        } else {
            return org.python.types.NotImplementedType.NOT_IMPLEMENTED;
        }
    }

    @org.python.Method(__doc__ = "Return self != datetime.")
    public org.python.Object __ne__(org.python.Object datetime) {
        if (datetime instanceof DateTime) {
            boolean res = !(this._eq((DateTime) datetime));
            return org.python.types.Bool.getBool(res);
        } else {
            return org.python.types.NotImplementedType.NOT_IMPLEMENTED;
        }
    }

    @org.python.Method(__doc__ = "Return self >= datetime.")
    public org.python.Object __ge__(org.python.Object datetime) {
        if (datetime instanceof DateTime) {
            boolean res = !(this._lt((DateTime) datetime));
            return org.python.types.Bool.getBool(res);
        } else {
            return org.python.types.NotImplementedType.NOT_IMPLEMENTED;
        }
    }

    @org.python.Method(__doc__ = "Return self > datetime.")
    public org.python.Object __gt__(org.python.Object datetime) {
        if (datetime instanceof DateTime) {
            boolean res = !(this._lt((DateTime) datetime)) && !(this._eq((DateTime) datetime));
            return org.python.types.Bool.getBool(res);
        } else {
            return org.python.types.NotImplementedType.NOT_IMPLEMENTED;
        }
    }

    private boolean _eq(DateTime datetime) {
        long y1 = ((org.python.types.Int) this.year).value;
        long m1 = ((org.python.types.Int) this.month).value;
        long d1 = ((org.python.types.Int) this.day).value;
        long h1 = ((org.python.types.Int) this.hour).value;
        long min1 = ((org.python.types.Int) this.minute).value;
        long s1 = ((org.python.types.Int) this.second).value;
        long ms1 = ((org.python.types.Int) this.microsecond).value;
        long y2 = ((org.python.types.Int) datetime.year).value;
        long m2 = ((org.python.types.Int) datetime.month).value;
        long d2 = ((org.python.types.Int) datetime.day).value;
        long h2 = ((org.python.types.Int) datetime.hour).value;
        long min2 = ((org.python.types.Int) datetime.minute).value;
        long s2 = ((org.python.types.Int) datetime.second).value;
        long ms2 = ((org.python.types.Int) datetime.microsecond).value;

        return y1 == y2 && m1 == m2 && d1 == d2 && h1 == h2 && min1 == min2 && s1 == s2 && ms1 == ms2;
    }

    private boolean _lt(DateTime datetime) {
        long y1 = ((org.python.types.Int) this.year).value;
        long m1 = ((org.python.types.Int) this.month).value;
        long d1 = ((org.python.types.Int) this.day).value;
        long h1 = ((org.python.types.Int) this.hour).value;
        long min1 = ((org.python.types.Int) this.minute).value;
        long s1 = ((org.python.types.Int) this.second).value;
        long ms1 = ((org.python.types.Int) this.microsecond).value;
        long y2 = ((org.python.types.Int) datetime.year).value;
        long m2 = ((org.python.types.Int) datetime.month).value;
        long d2 = ((org.python.types.Int) datetime.day).value;
        long h2 = ((org.python.types.Int) datetime.hour).value;
        long min2 = ((org.python.types.Int) datetime.minute).value;
        long s2 = ((org.python.types.Int) datetime.second).value;
        long ms2 = ((org.python.types.Int) datetime.microsecond).value;

        return y1 < y2
            || (y1 == y2 && m1 < m2)
            || (y1 == y2 && m1 == m2 && d1 < d2)
            || (y1 == y2 && m1 == m2 && d1 == d2 && h1 < h2)
            || (y1 == y2 && m1 == m2 && d1 == d2 && h1 == h2 && min1 < min2)
            || (y1 == y2 && m1 == m2 && d1 == d2 && h1 == h2 && min1 == min2 && s1 < s2)
            || (y1 == y2 && m1 == m2 && d1 == d2 && h1 == h2 && min1 == min2 && s1 == s2 && ms1 < ms2);
    }

}
