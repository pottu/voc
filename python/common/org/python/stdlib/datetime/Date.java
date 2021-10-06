package org.python.stdlib.datetime;

import java.util.Collections;

public class Date extends org.python.types.Object {

    @org.python.Attribute
    public org.python.Object year = __year__();

    @org.python.Attribute
    public org.python.Object month = __month__();

    @org.python.Attribute
    public org.python.Object day = __day__();

    @org.python.Attribute
    public static final org.python.Object min = __min__();

    @org.python.Attribute
    public static final org.python.Object max = __max__();

    @org.python.Method(__doc__ = "date(year, month, day) --> date object")
    public Date(org.python.Object[] args, java.util.Map<java.lang.String, org.python.Object> kwargs) {

        super();

        if (args.length + kwargs.size() > 3) {
            int val = args.length + kwargs.size();
            throw new org.python.exceptions.TypeError("function takes at most 3 arguments (" + val + " given)");
        }

        if (args.length + kwargs.size() == 3) {
            if (kwargs.get("year") != null && args.length == 2) {
                throw new org.python.exceptions.SyntaxError("positional argument follows keyword argument");
            } else if (kwargs.get("month") != null && args.length == 2) {
                throw new org.python.exceptions.SyntaxError("positional argument follows keyword argument");
            }
            if (kwargs.get("year") != null) {
                this.year = kwargs.get("year");
            } else {
                this.year = args[0];
            }

            if (kwargs.get("month") != null) {
                this.month = kwargs.get("month");
            } else {
                this.month = args[1];
            }

            if (kwargs.get("day") != null) {
                this.day = kwargs.get("day");
            } else {
                this.day = args[2];
            }

            if ((this.year instanceof org.python.types.Int) && (this.month instanceof org.python.types.Int) && (this.day instanceof org.python.types.Int)) {
                if (python.datetime.MINYEAR.value <= ((org.python.types.Int) this.year).value && ((org.python.types.Int) this.year).value <= python.datetime.MAXYEAR.value) {
                    if (1d <= ((org.python.types.Int) this.month).value && ((org.python.types.Int) this.month).value <= 12d) {
                        long[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                        java.util.GregorianCalendar gc = new java.util.GregorianCalendar();
                        if (gc.isLeapYear((int) ((org.python.types.Int) this.year).value)) {
                            daysInMonth[1] = 29;
                        }

                        if (!(1d <= ((org.python.types.Int) this.day).value && ((org.python.types.Int) this.day).value <= daysInMonth[(int) ((org.python.types.Int) this.month).value - 1])) {
                            throw new org.python.exceptions.ValueError("day is out of range for month");
                        }
                    } else {
                        throw new org.python.exceptions.ValueError("month must be in 1..12");
                    }
                } else {
                    throw new org.python.exceptions.ValueError("year " + this.year + " is out of range");
                }
            } else {
                if (!(this.year instanceof org.python.types.Int)) {
                    throw new org.python.exceptions.TypeError("an integer is required (got type " + this.year.typeName() + ")");
                }
                if (!(this.month instanceof org.python.types.Int)) {
                    throw new org.python.exceptions.TypeError("an integer is required (got type " + this.month.typeName() + ")");
                }
                if (!(this.day instanceof org.python.types.Int)) {
                    throw new org.python.exceptions.TypeError("an integer is required (got type " + this.day.typeName() + ")");
                }
            }
        }

        if (args.length + kwargs.size() == 2) {

            if (args.length == 2) {
                this.year = args[0];
                this.month = args[1];
            }

            if (kwargs.get("year") != null) {
                this.year = kwargs.get("year");
            } else if (args.length > 0) {
                this.year = args[0];
            }

            if (kwargs.get("month") != null) {
                this.month = kwargs.get("month");
            }
            if (kwargs.get("day") != null) {
                this.day = kwargs.get("day");
            }

            String y = this.year + "";
            String m = this.month + "";
            String d = this.day + "";

            if (!y.equals("null") && !(this.year instanceof org.python.types.Int)) {
                throw new org.python.exceptions.TypeError("an integer is required (got type " + this.year.typeName() + ")");
            }
            if (kwargs.get("year") != null && args.length > 0) {
                throw new org.python.exceptions.SyntaxError("positional argument follows keyword argument");
            }

            if (!(this.month instanceof org.python.types.Int) && !m.equals("null")) {
                throw new org.python.exceptions.TypeError("an integer is required (got type " + this.month.typeName() + ")");
            }

            if (y.equals("null")) {

                throw new org.python.exceptions.TypeError("function missing required argument 'year' (pos 1)");
            }

            if (m.equals("null")) {

                throw new org.python.exceptions.TypeError("function missing required argument 'month' (pos 2)");
            }
            if (d.equals("null")) {
                throw new org.python.exceptions.TypeError("function missing required argument 'day' (pos 3)");
            }
        }

        if (args.length + kwargs.size() == 1) {
            if (kwargs.get("year") != null) {
                this.year = kwargs.get("year");
            } else if (args.length > 0) {
                this.year = args[0];
            }
            if (kwargs.get("month") != null) {
                this.month = kwargs.get("month");
            }

            if (kwargs.get("day") != null) {
                this.day = kwargs.get("day");
            }

            String y = this.year + "";
            String m = this.month + "";
            String d = this.day + "";

            if (!(this.year instanceof org.python.types.Int) && !y.equals("null")) {
                throw new org.python.exceptions.TypeError("an integer is required (got type " + this.year.typeName() + ")");
            }
            if (!y.equals("null")) {
                throw new org.python.exceptions.TypeError("function missing required argument 'month' (pos 2)");
            }
            if (!m.equals("null") || !d.equals("null")) {
                throw new org.python.exceptions.TypeError("function missing required argument 'year' (pos 1)");
            }

        }
        if (args.length + kwargs.size() == 0) {
            throw new org.python.exceptions.TypeError("function missing required argument 'year' (pos 1)");
        }

    }

    @org.python.Method(__doc__ = "Implement setattr(self, name, value).")
    public void __setattr__(java.lang.String name, org.python.Object value) {
        if (name == "year" || name == "month" || name == "day") {
            throw new org.python.exceptions.AttributeError("attribute '" + name + "' of 'datetime.date' objects is not writable");
        } else {
            throw new org.python.exceptions.AttributeError("'datetime.date' object has no attribute '" + name + "'");
        }
    }



    @org.python.Method(__doc__ = "Return repr(self).")
    public org.python.types.Str __repr__() {

        String year = this.year + "";
        String month = this.month + "";
        String day = this.day + "";

        return new org.python.types.Str("datetime.date(" + year + ", " + month + ", " + day + ")");
    }

    @org.python.Method(__doc__ = "")
    public org.python.types.Str __year__() {
        return new org.python.types.Str(this.year + "");
    }

    @org.python.Method(__doc__ = "")
    public org.python.types.Str __month__() {
        return new org.python.types.Str(this.month + "");
    }

    @org.python.Method(__doc__ = "")
    public org.python.types.Str __day__() {
        return new org.python.types.Str(this.day + "");
    }

    @org.python.Method(__doc__ = "")
    private static org.python.Object __max__() {

        org.python.types.Int day = org.python.types.Int.getInt(31);
        org.python.types.Int month = org.python.types.Int.getInt(12);
        org.python.types.Int year = org.python.types.Int.getInt(9999);

        org.python.Object[] args = {year, month, day};
        return new Date(args, Collections.emptyMap());
    }

    @org.python.Method(__doc__ = "")

    private static org.python.Object __min__() {
        org.python.types.Int day = org.python.types.Int.getInt(1);
        org.python.types.Int month = org.python.types.Int.getInt(1);
        org.python.types.Int year = org.python.types.Int.getInt(1);

        org.python.Object[] args = {year, month, day};
        return new Date(args, Collections.emptyMap());

    }

    @org.python.Method(__doc__ = "Current date or datetime:  same as self.__class__.fromtimestamp(time.time()).")
    public static org.python.Object today() {
        java.time.LocalDateTime today = java.time.LocalDateTime.now();
        int y = today.getYear();
        int m = today.getMonthValue();
        int d = today.getDayOfMonth();

        org.python.Object[] args = {org.python.types.Int.getInt(y), org.python.types.Int.getInt(m), org.python.types.Int.getInt(d)};
        return new Date(args, Collections.emptyMap());
    }

    @org.python.Method(__doc__ = "Return ctime() style string.")
    public org.python.Object ctime() {
        String[] monthList = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        long monthNum = ((org.python.types.Int) this.month).value;
        String monthStr = monthList[(int) monthNum - 1];

        String[] weekdayList = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        long weekdayNum = ((org.python.types.Int) weekday()).value;
        String weekdayStr = weekdayList[(int) weekdayNum];

        return new org.python.types.Str(weekdayStr + " " + monthStr + " " + this.day + " 00:00:00 " + this.year);
    }

    @org.python.Method(__doc__ = "Return the day of the week represented by the date.\nMonday == 0 ... Sunday == 6")
    public org.python.Object weekday() {
        long y = ((org.python.types.Int) this.year).value;
        long m = ((org.python.types.Int) this.month).value;
        long d = ((org.python.types.Int) this.day).value;

        java.util.Date myCalendar = new java.util.GregorianCalendar((int) y, (int) m - 1, (int) d).getTime();
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(myCalendar);
        int day = c.get(java.util.Calendar.DAY_OF_WEEK);
        int[] convertToPython = {6, 0, 1, 2, 3, 4, 5};
        return org.python.types.Int.getInt(convertToPython[day - 1]);
    }

    @org.python.Method(__doc__ = "Return self<value.")
    public org.python.Object __lt__(org.python.Object other) {
        if (other instanceof Date) {
            boolean res = this._lessThan((Date) other);
            return org.python.types.Bool.getBool(res);
        } else {
            return org.python.types.NotImplementedType.NOT_IMPLEMENTED;
        }
    }

    @org.python.Method(__doc__ = "Return self<=value.")
    public org.python.Object __le__(org.python.Object other) {
        if (other instanceof Date) {
            boolean res = this._lessThan((Date) other)
                    || this._equals((Date) other);
            return org.python.types.Bool.getBool(res);
        } else {
            return org.python.types.NotImplementedType.NOT_IMPLEMENTED;
        }
    }

    @org.python.Method(__doc__ = "Return self==value.")
    public org.python.Object __eq__(org.python.Object other) {
        if (other instanceof Date) {
            boolean res = this._equals((Date) other);
            return org.python.types.Bool.getBool(res);
        } else {
            return org.python.types.NotImplementedType.NOT_IMPLEMENTED;
        }
    }

    @org.python.Method(__doc__ = "Return self!=value.")
    public org.python.Object __ne__(org.python.Object other) {
        if (other instanceof Date) {
            boolean res = !(this._equals((Date) other));
            return org.python.types.Bool.getBool(res);
        } else {
            return org.python.types.NotImplementedType.NOT_IMPLEMENTED;
        }
    }

    @org.python.Method(__doc__ = "Return self>=value.")
    public org.python.Object __ge__(org.python.Object other) {
        if (other instanceof Date) {
            boolean res = !(this._lessThan((Date) other));
            return org.python.types.Bool.getBool(res);
        } else {
            return org.python.types.NotImplementedType.NOT_IMPLEMENTED;
        }
    }

    @org.python.Method(__doc__ = "Return self>value.")
    public org.python.Object __gt__(org.python.Object other) {
        if (other instanceof Date) {
            boolean res = !(this._lessThan((Date) other))
                    && !(this._equals((Date) other));
            return org.python.types.Bool.getBool(res);
        } else {
            return org.python.types.NotImplementedType.NOT_IMPLEMENTED;
        }
    }

    private boolean _equals(Date other) {
        long y1 = ((org.python.types.Int) this.year).value;
        long m1 = ((org.python.types.Int) this.month).value;
        long d1 = ((org.python.types.Int) this.day).value;
        long y2 = ((org.python.types.Int) other.year).value;
        long m2 = ((org.python.types.Int) other.month).value;
        long d2 = ((org.python.types.Int) other.day).value;

        return y1 == y2 && m1 == m2 && d1 == d2;
    }

    private boolean _lessThan(Date other) {
        long y1 = ((org.python.types.Int) this.year).value;
        long m1 = ((org.python.types.Int) this.month).value;
        long d1 = ((org.python.types.Int) this.day).value;
        long y2 = ((org.python.types.Int) other.year).value;
        long m2 = ((org.python.types.Int) other.month).value;
        long d2 = ((org.python.types.Int) other.day).value;

        return y1 < y2
            || (y1 == y2 && m1 < m2)
            || (y1 == y2 && m1 == m2 && d1 < d2);
    }

    @org.python.Method(__doc__ = "Return string in ISO 8601 format, YYYY-MM-DD.")
    public org.python.Object isoformat() {
        long y = ((org.python.types.Int) this.year).value;
        long m = ((org.python.types.Int) this.month).value;
        long d = ((org.python.types.Int) this.day).value;
        String res = String.format("%04d-%02d-%02d", y, m, d);
        return new org.python.types.Str(res);
    }

    @org.python.Method(__doc__ = "str -> Construct a date from the output of date.isoformat()")
    public static org.python.Object fromisoformat(org.python.Object dateString) {
        if (dateString instanceof org.python.types.Str) {
            String date = ((org.python.types.Str) dateString).value;
            if (date.length() == 10
                    && Character.isDigit(date.charAt(0))
                    && Character.isDigit(date.charAt(1))
                    && Character.isDigit(date.charAt(2))
                    && Character.isDigit(date.charAt(3))
                    && date.charAt(4) == '-'
                    && Character.isDigit(date.charAt(5))
                    && Character.isDigit(date.charAt(6))
                    && date.charAt(7) == '-'
                    && Character.isDigit(date.charAt(8))
                    && Character.isDigit(date.charAt(9))) {
                long year = Long.parseLong(date.substring(0, 4));
                long month = Long.parseLong(date.substring(5, 7));
                long day = Long.parseLong(date.substring(8, 10));
                org.python.types.Int d = org.python.types.Int.getInt(day);
                org.python.types.Int m = org.python.types.Int.getInt(month);
                org.python.types.Int y = org.python.types.Int.getInt(year);
                org.python.Object[] args = {y, m, d};
                return new Date(args, Collections.emptyMap());
            } else {
                throw new org.python.exceptions.ValueError("Invalid isoformat string: '" + date + "'");
            }
        } else {
            throw new org.python.exceptions.TypeError("descriptor 'isoformat' for 'datetime.date' objects doesn't apply to a '" + dateString.typeName() + "' object");
        }
    }
}
