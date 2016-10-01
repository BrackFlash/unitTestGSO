/*
 *
 */
package fontys.time;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Stan
 */
public class Appointment {

    private String subject;
    private ITimeSpan timeSpan;
    private ArrayList<Contact> invitees;

    /**
     *
     * @param subject a string containting the subject of the appointment, can
     * be empty
     * @param timeSpan the timespan during which the appointment will take place
     */
    public Appointment(String subject, ITimeSpan timeSpan) {
        this.subject = subject;
        this.timeSpan = timeSpan;
        invitees = new ArrayList<>();

    }

    /**
     *
     * @return the subject of this appointment
     */
    public String getSubject() {
        return subject;
    }

    /**
     *
     * @return the timespan of this appointment
     */
    public ITimeSpan getTimeSpan() {
        return timeSpan;
    }

    /**
     *
     * @return an iterator of the invitees list
     */
    public Iterator<Contact> invitees() {
        Iterator<Contact> inviteesIterator = invitees.iterator();
        return inviteesIterator;
    }

    /**
     * adds a
     *
     * @param c the contact that needs to be added, can only be added if the
     * current appointment does not overlap with other appoitments that are in
     * his/her agenda
     * @return boolean, false if it adding failed and true if it succeeded
     */
    public boolean addContact(Contact c) {
        if (!c.appointments().hasNext()) {
            this.invitees.add(c);
            c.addAppointment(this);
            System.out.println("Ammount of contacts: " + this.invitees.size());
            return true;
        } else if (c.addAppointment(this) == true) {
            return true;
        } 
        return false;
    }

    /**
     * Removes a contact from list
     *
     * @param c the contact that is supposed to be removed, has to be in list
     */
    public void removeContact(Contact c) {
        for (Iterator<Contact> inviteesIt = this.invitees(); inviteesIt.hasNext();) {
            System.out.println("Test");
            if (inviteesIt.next() == c) {
                invitees.remove(c);
            }
        }
    }

}
