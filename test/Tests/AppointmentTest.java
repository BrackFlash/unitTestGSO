/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import fontys.time.Appointment;
import fontys.time.Contact;
import fontys.time.Time;
import fontys.time.TimeSpan;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Stan van der Vleuten
 */
public class AppointmentTest {

	private Contact c1;
	private Contact c2;
	private Appointment a1;
	private Appointment a2;
	private Appointment a3;

	@Before
	public void setUp() {
		c1 = new Contact("jan");
		c2 = new Contact("henk");
		a1 = new Appointment("test", new TimeSpan(new Time(1, 1, 1, 1, 1), new Time(2, 2, 2, 2, 2)));
		a2 = new Appointment("test2", new TimeSpan(new Time(1, 1, 1, 1, 1), new Time(2, 2, 2, 2, 2)));
		a3 = new Appointment("test3", new TimeSpan(new Time(3, 3, 3, 3, 3), new Time(4, 4, 4, 4, 4)));
	}

	@Test
	/**
	 * tests if the construccter of is setting names properly test of the
	 * contructor of the Appointment class, subject can be empty
	 */
	public void appointmentTest() {
		a1 = new Appointment("test", new TimeSpan(new Time(1, 1, 1, 1, 1), new Time(2, 2, 2, 2, 2)));
		assertNotEquals(null, a1);
		assertEquals("test", a1.getSubject());
		a1 = new Appointment("", new TimeSpan(new Time(1, 1, 1, 1, 1), new Time(2, 2, 2, 2, 2)));
		assertEquals("", a1.getSubject());

	}

	@Test
	/**
	 * tests to see if the name that is set with the constructer is returned
	 * properly
	 *
	 * test for the getSubject method of the Appointment class
	 */
	public void getSubjectTest() {
		assertEquals("test", a1.getSubject());
	}

	@Test
	/**
	 * test for the getTimepsan method of the Appointment class tests to see
	 * if the timespan that is set with the constructer is returned properly
	 */
	public void getTimespanTest() {
		TimeSpan ts;
		ts = new TimeSpan(new Time(1, 1, 1, 1, 1), new Time(2, 2, 2, 2, 2));
		a1 = new Appointment("test", ts);
		TimeSpan ts2;
		ts2 = (TimeSpan) a1.getTimeSpan();
		assertSame(ts, ts2);

	}

	@Test
	/**
	 * test for the invitees method of the Appointment class tests if the
	 * inventees iterator is working properly
	 */
	public void inviteesTest() {
		a1.addContact(c1);
		a1.addContact(c2);
		Iterator<Contact> it = a1.invitees();
		assertSame(c1, it.next());
		assertSame(c2, it.next());
	}

	@Test
	/**
	 * test for the addContact method of the Appointmentclass the contact
	 * that needs to be added, can only be added if the current appointment
	 * does not overlap with other appoitments that are in his/her agenda
	 */
	public void addContactTest() {

		assertTrue(a1.addContact(c1));
		assertFalse(a2.addContact(c1));
		assertTrue(a3.addContact(c1));

	}

	@Test
	/**
	 * test for the removeContact method of the Appointment class
	 */
	public void removeContactTest() {
		a1.addContact(c1);
		assertFalse(a1.addContact(c1));
		a1.removeContact(c1);
		assertTrue(a1.addContact(c1));
		a1.removeContact(c2);

	}

}
