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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author stan
 */
public class AppointmentTest {

	private Appointment appointment;

	@Before
	public void setUp() {
		appointment = new Appointment("test", new TimeSpan(new Time(3, 3, 3, 3, 3), new Time(4, 4, 4, 4, 4)));
	}

	@After
	public void tearDown() {
	}

	@Test
	/**
	 * test of the contructor of the Appointment class, subject can be empty
	 */
	public void appointmentTest() {
		appointment = new Appointment("test", new TimeSpan(new Time(1, 1, 1, 1, 1), new Time(2, 2, 2, 2, 2)));
		assertNotEquals(null, appointment);
		appointment = new Appointment("", new TimeSpan(new Time(1, 1, 1, 1, 1), new Time(2, 2, 2, 2, 2)));
		assertEquals("", appointment.getSubject());

	}

	@Test
	/**
	 * test for the getSubject method of the Appointment class
	 */
	public void getSubjectTest() {
		assertEquals("test", appointment.getSubject());

	}

	@Test
	/**
	 * test for the getTimepsan method of the Appointment class
	 */
	public void getTimespanTest() {
		TimeSpan ts;
		ts = new TimeSpan(new Time(1, 1, 1, 1, 1), new Time(2, 2, 2, 2, 2));
		appointment = new Appointment("test", ts);
		TimeSpan ts2;
		ts2 = (TimeSpan) appointment.getTimeSpan();
		assertSame(ts, ts2);

	}

	@Test
	/**
	 * test for the invitees method of the Appointment class
	 */
	public void inviteesTest() {
		Contact c1 = new Contact("jan");
		Contact c2 = new Contact("henk");
		appointment.addContact(c1);
		appointment.addContact(c2);
		Iterator<Contact> it = appointment.invitees();
		assertSame(c1, it.next());
		assertSame(c2, it.next());
	}

	@Test
	/**
	 * test for the addContact method of the Appointment class
	 */
	public void addContactTest() {
		//TODO
	}

	@Test
	/**
	 * test for the removeContact method of the Appointment class
	 */
	public void removeContactTest() {
		//TODO
	}

}
