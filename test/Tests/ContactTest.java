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
 * @author Paul
 */
public class ContactTest {

    private Contact contact;
    private Appointment firstAppointment;
    private Appointment secondAppointment;
    private Appointment thirdAppointment;

    @Before
    public void setUp() {
        this.contact = new Contact("Henk");
        this.firstAppointment = new Appointment("Unit testen GSO", new TimeSpan(new Time(2016, 9, 20, 10, 10), new Time(2016, 9, 25, 15, 15)));
        this.secondAppointment = new Appointment("Naar huis gaan", new TimeSpan(new Time(2016, 9, 24, 10, 10), new Time(2016, 10, 1, 1, 1)));
        this.thirdAppointment = new Appointment("Unit testen is leuk", new TimeSpan(new Time(2015, 9, 24, 10, 10), new Time(2015, 10, 1, 1, 1)));
    }

    /**
     * Tests if the constructor works properly
     * Checks the assignment of the name of the contact
     */
    @Test
    public void testConstructor() {
        assertEquals("Henk", this.contact.getName());
        assertNotNull(this.contact.appointments());
    }

    /**
     * Testing the add method of the contact/appointment class.
     * First case: the person has no appointments. An appointments should be added to the agenda of the person
     * Second case: the appointment timespan intersects. The method should return false.
     * Third case: an appointment is added because the appointment doesn't intersect with the agenda of the person
     */
    @Test
    public void testAddAppointment() {
        assertTrue(this.firstAppointment.addContact(this.contact));
        assertFalse(this.secondAppointment.addContact(this.contact));
        assertTrue(this.thirdAppointment.addContact(this.contact));
    }

    @Test
    public void testRemoveAppointment() {
        assertFalse(this.thirdAppointment.invitees().hasNext());
        this.thirdAppointment.addContact(this.contact);
        thirdAppointment.removeContact(this.contact);
        assertFalse(this.thirdAppointment.invitees().hasNext());
    }

}
