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
 * @author Paul Pham
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
        assertEquals(timeSpan.getEndTime().getYear(), endTime.getYear());
        assertEquals(timeSpan.getEndTime().getMonth(), endTime.getMonth());
        assertEquals(timeSpan.getEndTime().getDay(), endTime.getDay());
        assertEquals(timeSpan.getEndTime().getMinutes(), endTime.getMinutes());
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
        Time newFailEndTime = new Time(1999, 11, 12, 12, 12);
        timeSpan.setEndTime(newFailEndTime);
    }

    /**
     * Test SetEndTime method of class TimeSpan.
     */
    @Test
    public void testSetEndTime() {
        Time newEndTime = new Time(2002, 11, 12, 12, 12);
        timeSpan.setEndTime(newEndTime);
        assertEquals(timeSpan.getEndTime().getYear(), newEndTime.getYear());
        assertEquals(timeSpan.getEndTime().getMonth(), newEndTime.getMonth());
        assertEquals(timeSpan.getEndTime().getDay(), newEndTime.getDay());
        assertEquals(timeSpan.getEndTime().getMinutes(), newEndTime.getMinutes());
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
        assertEquals(22, timeSpan.getBeginTime().getMinutes());
        assertEquals(22, timeSpan.getEndTime().getMinutes());
        //timeSpan.move(-10);
        //assertEquals(timeSpan.getBeginTime().getMinutes(), beginTime.getMinutes());
        //assertEquals(timeSpan.getEndTime().getMinutes(), endTime.getMinutes());

    }

    /**
     * Test Exception throwing of changeLength method of class TimeSpan.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testChangeLengthEx() {
        System.out.println(timeSpan.getEndTime().getMinutes());
        timeSpan.changeLengthWith(-12000000);
        System.out.println(timeSpan.getEndTime().getMinutes());

    }

    /**
     * Test changeLength method of class TimeSpan. minutes + length of this
     * period must be positive
     */
    @Test
    public void testChangeLength() {
        assertEquals(12, timeSpan.getEndTime().getMinutes());
        timeSpan.changeLengthWith(1);
        assertEquals(13, timeSpan.getEndTime().getMinutes());

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
        assertEquals(true, nts.isPartOf(timeSpan));

        nbt = new Time(2101, 11, 11, 12, 12);
        net = new Time(2101, 11, 12, 12, 12);
        nts = new TimeSpan2(nbt, net);
        assertEquals(false, nts.isPartOf(timeSpan));

        nbt = new Time(0, 2, 2, 2, 2);
        net = new Time(2201, 11, 11, 11, 11);
        nts = new TimeSpan2(nbt, net);
        assertEquals(false, nts.isPartOf(timeSpan));

    }

    /**
     * Test intersectionwith method of class TimeSpan. niet helemaal gelukt,
     * grotendeels door het niet begrijpen van de methode, navragen tijdens de
     * les.
     */
    @Test
    public void testintersectionWidth() {

        Time bt = new Time(2001, 1, 1, 1, 1);
        Time et = new Time(2001, 12, 12, 12, 12);
        TimeSpan2 ts = new TimeSpan2(bt, et);

        Time nbt = new Time(2001, 2, 2, 2, 2);
        Time net = new Time(2001, 11, 11, 11, 11);
        TimeSpan2 nts = new TimeSpan2(nbt, net);

        TimeSpan2 nnts = (TimeSpan2) ts.intersectionWith(nts);
        assertEquals(1, nnts.getBeginTime().getMonth());
        assertEquals(10, nnts.getEndTime().getMonth());

        nnts = (TimeSpan2) nts.intersectionWith(ts);
        System.out.println(nnts.getBeginTime().getMonth());
        System.out.println(nnts.getEndTime().getMonth());
        assertEquals(1, nnts.getBeginTime().getMonth());
        assertEquals(10, nnts.getEndTime().getMonth());

        bt = new Time(2001, 3, 3, 3, 3);
        et = new Time(2001, 4, 4, 4, 4);

        ts = new TimeSpan2(bt, et);

        nbt = new Time(2003, 1, 1, 1, 1);
        net = new Time(2004, 2, 2, 2, 2);
        nts = new TimeSpan2(nbt, net);
        nnts = (TimeSpan2) ts.intersectionWith(nts);
        assertEquals(null, nnts);

    }

    /**
     * Test unionwith method of class TimeSpan. niet gelukt, grotendeels door
     * het niet begrijpen van de methode, navragen tijdens de les.
     *
     */
    @Test
    public void testUnionWidth() {

        Time bt = new Time(2001, 1, 1, 1, 1);
        Time et = new Time(2001, 5, 5, 5, 5);

        TimeSpan2 ts = new TimeSpan2(bt, et);

        Time nbt = new Time(2001, 5, 5, 5, 5);
        Time net = new Time(2001, 6, 6, 6, 6);
        TimeSpan2 nts = new TimeSpan2(nbt, net);

        TimeSpan2 nnts = (TimeSpan2) ts.unionWith(nts);
        assertNotEquals(null, nnts);

        nnts = (TimeSpan2) nts.unionWith(ts);
        assertNotEquals(null, nnts);

        bt = new Time(2001, 1, 1, 1, 1);
        et = new Time(2001, 2, 2, 2, 2);
        ts = new TimeSpan2(bt, et);

        nbt = new Time(2001, 3, 3, 3, 3);
        net = new Time(2001, 4, 4, 4, 4);
        nts = new TimeSpan2(nbt, net);

        nnts = (TimeSpan2) ts.unionWith(nts);
        assertEquals(null, nnts);
        nnts = (TimeSpan2) nts.unionWith(ts);
        assertEquals(null, nnts);
    }

}
