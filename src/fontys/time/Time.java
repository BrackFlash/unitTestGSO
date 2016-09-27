/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

import java.util.GregorianCalendar;

/**
 *
 * @author Paul Pham
 *
 * LET OP: De klasse Time bevat enkele fouten.
 *
 */
public class Time implements ITime {

    private GregorianCalendar gc;

    /**
     * creation of a time-object with year y, month m, day d, hours h and
     * minutes m; if the combination of y-m-d refers to a non-existing date the
     * value of this Time-object will be not guaranteed
     *
     * MONTHS START WITH 0 AND END AT 11!!!
     *
     * @param y
     * @param m 0≤m≤11
     * @param d 1≤d≤31
     * @param h 0≤h≤23
     * @param min 0≤m≤59
     */
    public Time(int y, int m, int d, int h, int min) {
        if (m < 1 || m > 12) {
            throw new IllegalArgumentException("month must be within 1..12");
        }
        if (d < 1 || d > 31) {
            throw new IllegalArgumentException("day must be within 1..31");
        }
        if (h < 0 || h > 23) {
            throw new IllegalArgumentException("hours must be within 0..23");
        }
        //Minutes must be between 0-59
        if (min < 0 || min > 59) {
            throw new IllegalArgumentException("minutes must be within 0..59");
        }
        //GregorianCalender months must be between 0 and 11. Therefore in the constructor the month = month -1;
        gc = new GregorianCalendar(y, m - 1, d, h, min);
    }

    public Time(Time t) {
        gc = (GregorianCalendar) t.gc.clone();
    }

    /**
     * If you want to test all cases of the switch, the given parameter should be -1.
     * In all other cases it doesn't matter what integer you put in.
     * @param testDay
     * @return 
     */
    @Override
    public DayInWeek getDayInWeek() {
        int day_of_week = gc.get(GregorianCalendar.DAY_OF_WEEK);
        /*if (testDay == -1) {
            day_of_week = testDay;
        }*/
        switch (day_of_week) {
            case GregorianCalendar.SUNDAY:
                return DayInWeek.SUN;
            case GregorianCalendar.MONDAY:
                return DayInWeek.MON;
            case GregorianCalendar.TUESDAY:
                return DayInWeek.TUE;
            case GregorianCalendar.WEDNESDAY:
                return DayInWeek.WED;
            case GregorianCalendar.THURSDAY:
                return DayInWeek.THU;
            case GregorianCalendar.FRIDAY:
                return DayInWeek.FRI;
            case GregorianCalendar.SATURDAY:
                return DayInWeek.SAT;
            default:
                return null;
        }
    }

    @Override
    public int getYear() {
        return gc.get(GregorianCalendar.YEAR);
    }

    @Override
    public int getMonth() {
        return gc.get(GregorianCalendar.MONTH);
    }

    @Override
    public int getDay() {
        return gc.get(GregorianCalendar.DAY_OF_MONTH);
    }

    @Override
    public int getHours() {
        return gc.get(GregorianCalendar.HOUR_OF_DAY);
    }

    @Override
    public int getMinutes() {
        return gc.get(GregorianCalendar.MINUTE);
    }

    @Override
    public ITime plus(int minutes) {
        gc.add(GregorianCalendar.MINUTE, minutes);
        return this;
    }

    @Override
    public int compareTo(ITime t) {
        Time time = (Time) t;
        return gc.compareTo(time.gc);
    }

    @Override
    public int difference(ITime time) {
        Time t = (Time) time;
        //return (int) (this.gc.getTimeInMillis() - t.gc.getTimeInMillis()) / 600000);
        //Er was een nul teveel        
        return (int) (this.gc.getTimeInMillis() - t.gc.getTimeInMillis()) / 60000;
    }
}
