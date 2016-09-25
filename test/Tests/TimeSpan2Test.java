package Tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import fontys.time.Time;
import fontys.time.TimeSpan2;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stan van der Vleuten
 */
public class TimeSpan2Test {

    public TimeSpan2Test() {
    }

    private TimeSpan2 timeSpan;
    private Time beginTime;
    private Time endTime;

    @Before
    public void setUp() {
        beginTime = new Time(2000, 11, 12, 12, 12);
        endTime = new Time(2001, 11, 12, 12, 12);
        timeSpan = new TimeSpan2(beginTime, endTime);
    }

    /**
     * Test of constructor of class TimeSpan.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTimeSpan() {
        timeSpan = new TimeSpan2(new Time(2001, 11, 12, 12, 12), new Time(2000, 11, 12, 12, 12));
    }

    /**
     * Test of getBeginTime method of class TimeSpan.
     *
     */
    @Test
    public void testGetBeginTime() {
        assertSame(timeSpan.getBeginTime(), beginTime);
    }

    /**
     * Test of getEndTime method of class TimeSpan.
     */
    @Test
    public void testGetEndTime() {
        /*System.out.println(timeSpan.getEndTime().getYear() + " --> " + endTime.getYear());
        System.out.println(timeSpan.getEndTime().getMonth() + " --> " + endTime.getMonth());
        System.out.println(timeSpan.getEndTime().getDay() + " --> " + endTime.getDay());
        System.out.println(timeSpan.getEndTime().getHours() + " --> " + endTime.getHours());
        System.out.println(timeSpan.getEndTime().getMinutes() + " --> " + endTime.getMinutes());
        System.out.println(timeSpan.getEndTime().getDayInWeek(0) + " --> " + endTime.getDayInWeek(0));
         */
        assertSame(timeSpan.getEndTime(), endTime);
    }

    /**
     * beginTime must be earlier than the current end time of this time span
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetBeginTimeEx() {
        assertSame(timeSpan.getBeginTime(), beginTime);
        Time nbt = new Time(2002, 11, 12, 12, 12);
        timeSpan.setBeginTime(nbt);
    }

    /**
     * Test SetBeginTime method of class TimeSpan.
     */
    @Test
    public void testSetBeginTime() {
        assertSame(timeSpan.getBeginTime(), beginTime);
        Time nbt = new Time(1999, 11, 12, 12, 12);
        timeSpan.setBeginTime(nbt);
        assertSame(timeSpan.getBeginTime(), nbt);
    }

    /**
     * Test Exception throwing of setEndTime method of class TimeSpan. endTime
     * must be later than the current begin time
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetEndTimeEx() {
        assertSame(timeSpan.getEndTime(), endTime);
        Time net = new Time(1999, 11, 12, 12, 12);
        timeSpan.setEndTime(net);
    }

    /**
     * Test SetEndTime method of class TimeSpan.
     */
    @Test
    public void testSetEndTime() {
        assertSame(timeSpan.getEndTime(), endTime);
        Time net = new Time(2002, 11, 12, 12, 12);
        timeSpan.setEndTime(net);
        assertSame(timeSpan.getEndTime(), net);
    }

    /**
     * Test length method of class TimeSpan.
     */
    @Test
    public void testLength() {
        beginTime = new Time(2000, 11, 12, 12, 0);
        endTime = new Time(2000, 11, 12, 12, 59);
        timeSpan = new TimeSpan2(beginTime, endTime);
        assertEquals(timeSpan.length(), endTime.difference(beginTime));

    }

    /**
     * Test Move method of class TimeSpan. (a negative value is allowed)
     */
    @Test
    public void testMove() {

        assertEquals(timeSpan.getBeginTime().getMinutes(), beginTime.getMinutes());
        assertEquals(timeSpan.getEndTime().getMinutes(), endTime.getMinutes());
        timeSpan.move(10);
        assertEquals(timeSpan.getBeginTime().getMinutes(), (beginTime.getMinutes() + 10));
        assertEquals(timeSpan.getEndTime().getMinutes(), (endTime.getMinutes() + 10));
        timeSpan.move(-10);
        assertEquals(timeSpan.getBeginTime().getMinutes(), beginTime.getMinutes());
        assertEquals(timeSpan.getEndTime().getMinutes(), endTime.getMinutes());

    }

    /**
     * Test Exception throwing of changeLength method of class TimeSpan.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testChangeLengthEx() {
        timeSpan.changeLengthWith(-1);

    }

    /**
     * Test changeLength method of class TimeSpan. minutes + length of this
     * period must be positive
     */
    @Test
    public void testChangeLength() {
        assertEquals(timeSpan.getEndTime().getMinutes(), endTime.getMinutes());
        timeSpan.changeLengthWith(1);
        assertEquals(timeSpan.getEndTime().getMinutes(), (endTime.getMinutes() + 1));

    }

    /**
     * Test isPartOf method of class TimeSpan. true if all moments within this
     * time span are included within [timeSpan], otherwise false
     */
    @Test
    public void testIsPartOf() {

        Time nbt = new Time(2001, 2, 2, 2, 2);
        Time net = new Time(2001, 11, 11, 11, 11);
        TimeSpan2 nts = new TimeSpan2(nbt, net);
        assertEquals(true, timeSpan.isPartOf(nts));

        nbt = new Time(2000, 11, 12, 12, 12);
        net = new Time(2101, 11, 12, 12, 12);
        nts = new TimeSpan2(nbt, net);
        assertEquals(false, timeSpan.isPartOf(nts));

        nbt = new Time(0, 2, 2, 2, 2);
        net = new Time(2201, 11, 11, 11, 11);
        nts = new TimeSpan2(nbt, net);
        assertEquals(false, timeSpan.isPartOf(nts));

    }

    /**
     * Test intersectionwith method of class TimeSpan. niet helemaal gelukt,
     * grotendeels door het niet begrijpen van de methode, navragen tijdens de
     * les.
     */
    @Test
    public void testintersectionWidth() {

        Time bt = new Time(2001, 1, 1, 1, 1);
        Time et = new Time(2001, 11, 12, 12, 12);
        TimeSpan2 ts = new TimeSpan2(bt, et);

        Time nbt = new Time(2001, 2, 2, 2, 2);
        Time net = new Time(2001, 11, 11, 11, 11);
        TimeSpan2 nts = new TimeSpan2(nbt, net);

        TimeSpan2 nnts = (TimeSpan2) ts.intersectionWith(nts);
        assertEquals(2, nnts.getBeginTime().getMonth());
        assertEquals(12, nnts.getEndTime().getMonth());

        nnts = (TimeSpan2) nts.intersectionWith(ts);
        System.out.println(nnts.getBeginTime().getMonth());
        System.out.println(nnts.getEndTime().getMonth());
        assertEquals(2, nnts.getBeginTime().getMonth());
        assertEquals(12, nnts.getEndTime().getMonth());

        /*bt = new Time(2001, 2, 2, 2, 2);
		et = new Time(2001, 4, 4, 4, 4);

		ts = new TimeSpan(bt, et);

		nbt = new Time(2001, 1, 1, 1, 1);
		net = new Time(2001, 2, 2, 2, 23);
		nts = new TimeSpan(nbt, net);
		nnts = (TimeSpan) ts.intersectionWith(nts);
		assertEquals(null, nnts);
         */
    }

    /**
     * Test unionwith method of class TimeSpan. niet gelukt, grotendeels door
     * het niet begrijpen van de methode, navragen tijdens de les.
     *
     */
    @Test
    public void testUnionWidth() {

        Time bt = new Time(2001, 1, 1, 1, 1);
        Time et = new Time(2001, 3, 3, 3, 3);

        TimeSpan2 ts = new TimeSpan2(bt, et);

        Time nbt = new Time(2001, 5, 5, 5, 5);
        Time net = new Time(2001, 6, 6, 6, 6);
        TimeSpan2 nts = new TimeSpan2(nbt, net);

        TimeSpan2 nnts = (TimeSpan2) ts.unionWith(nts);
        assertEquals(null, nnts);

        nnts = (TimeSpan2) nts.unionWith(ts);
        assertEquals(null, nnts);

        bt = new Time(2001, 1, 1, 1, 1);
        et = new Time(2001, 4, 4, 4, 4);
        ts = new TimeSpan2(bt, et);

        nbt = new Time(2001, 2, 2, 2, 2);
        net = new Time(2001, 3, 3, 3, 3);
        nts = new TimeSpan2(nbt, net);

        nnts = (TimeSpan2) ts.unionWith(nts);
        assertEquals(null, nnts);

    }

}
