/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import fontys.time.DayInWeek;
import fontys.time.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sun.util.calendar.Gregorian;

/**
 *
 * @author Paul Pham
 */
public class TimeTest {

    /**
     *
     */
    public TimeTest() {
    }

    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }

    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     *
     */
    @Before
    public void setUp() {
    }

    /**
     *
     */
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    /**
     *
     */
    @Test
    public void testTimeConstructorSuccesFull() {
        Time time = new Time(2016, 9, 21, 21, 21);
        assertEquals(2016, time.getYear());
        assertEquals(8, time.getMonth());
        assertEquals(21, time.getDay());
        assertEquals(21, time.getHours());
        assertEquals(21, time.getMinutes());
    }

    /**
     * Tests if month parameter is between 1 and 12
     */
    @Test
    public void testMonthTimeConstructorFail() {
        try {
            Time time = new Time(1, -1, 1, 1, 1);
            fail("It should go in the catch statement otherwise it failed, month < 1");
        } catch (IllegalArgumentException ex) {

        }
        try {
            Time time = new Time(1, 13, 1, 1, 1);
            fail("It should go in the catch statement otherwise it failed, month > 12");
        } catch (IllegalArgumentException ex) {

        }
    }

    /**
     * Tests if day parameter is between 1 and 31
     */
    @Test
    public void testDayTimeConstructorFail() {
        try {
            Time time = new Time(1, 1, 0, 1, 1);
            fail("It should go in the catch statement otherwise it failed, day < 1");
        } catch (IllegalArgumentException ex) {

        }
        try {
            Time time = new Time(1, 1, 32, 1, 1);
            fail("It should go in the catch statement otherwise it failed, day > 31");
        } catch (IllegalArgumentException ex) {

        }
    }

    /**
     * Tests if hour parameter is between 0 and 23
     */
    @Test
    public void testHourTimeConstructorFail() {
        try {
            Time time = new Time(1, 1, 1, -1, 1);
            fail("It should go in the catch statement otherwise it failed, hour < 0");
        } catch (IllegalArgumentException ex) {

        }
        try {
            Time time = new Time(1, 1, 1, 25, 1);
            fail("It should go in the catch statement otherwise it failed, hour > 24");
        } catch (IllegalArgumentException ex) {

        }
    }

    /**
     * Tests if minute parameter is between 1 and 60
     */
    @Test
    public void testMinuteTimeConstructorFail() {
        try {
            Time time = new Time(1, 1, 1, 1, -1);
            fail("It should go in the catch statement otherwise it failed, minute < 0");
        } catch (IllegalArgumentException ex) {

        }
        try {
            Time time = new Time(1, 1, 1, 1, 61);
            fail("It should go in the catch statement otherwise it failed, minute > 59");
        } catch (IllegalArgumentException ex) {

        }
    }

    @Test
    public void testAddMinutes() {
        Time time = new Time(1, 1, 1, 1, 1);
        time.plus(5);
        assertEquals(6, time.getMinutes());
    }

    @Test
    public void testDays() {
        for (int i = 1; i < 9; i++) {
            Time time = new Time(2016, 9, i, 12, 29);
            switch (i) {
                case 1:
                    assertEquals(DayInWeek.THU, time.getDayInWeek());
                    break;
                case 2:
                    assertEquals(DayInWeek.FRI, time.getDayInWeek());
                    break;
                case 3:
                    assertEquals(DayInWeek.SAT, time.getDayInWeek());
                    break;
                case 4:
                    assertEquals(DayInWeek.SUN, time.getDayInWeek());
                    break;
                case 5:
                    assertEquals(DayInWeek.MON, time.getDayInWeek());
                    break;
                case 6:
                    assertEquals(DayInWeek.TUE, time.getDayInWeek());
                    break;
                case 7:
                    assertEquals(DayInWeek.WED, time.getDayInWeek());
                    break;
                case 8:
                    //assertEquals(null, time.getDayInWeek());
                    break;
            }
        }
        //assertEquals(null, );
    }

    @Test
    public void testCompareTo() {
        Time time1 = new Time(1, 1, 1, 1, 1);
        Time time2 = new Time(2, 2, 2, 2, 2);
        Time time3 = new Time(1, 1, 1, 1, 1);
        ArrayList<Time> times = new ArrayList<>();
        times.add(time1);
        times.add(time2);
        Collections.sort(times);
    }

    @Test
    public void testTimeConstructor() {
        Time time = new Time(new Time(2016, 8, 24, 12, 1));
        assertEquals(2016, time.getYear());
        assertEquals(7, time.getMonth());
        assertEquals(12, time.getHours());
        assertEquals(1, time.getMinutes());
        assertEquals(24, time.getDay());
        assertEquals(DayInWeek.WED, time.getDayInWeek());
    }

    @Test
    public void testDifference() {
        Time time1 = new Time(2016, 8, 24, 12, 0);
        Time time2 = new Time(2016, 8, 24, 14, 30);
        System.out.println("Difference: " + time2.difference(time1));
    }

}
