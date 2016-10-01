/*
 *
 */
package fontys.time;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Stan
 */
public class Appointment {

	private String subject;
	private ITimeSpan timeSpan;
	private List<Contact> invitees;

	/**
	 *
	 * @param subject a string containting the subject of the appointment,
	 * can be empty
	 * @param timeSpan the timespan during which the appointment will take
	 * place
	 */
	public Appointment(String subject, ITimeSpan timeSpan) {
		this.subject = subject;
		this.timeSpan = timeSpan;

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
	 * @param c the contact that needs to be added, can only be added if the
	 * current appointment does not overlap with other appoitments that are
	 * in his/her agenda
	 * @return boolean, false if it adding failed and true if it succeeded
	 */
	public boolean addContact(Contact c) {
		while (c.appointments().hasNext()) {
			if (c.appointments().next().getTimeSpan().intersectionWith(timeSpan) != null) {
				return false;
			}
		}
		this.invitees.add(c);
		return true;
	}

	/**
	 * Removes a contact from list, when the contact is not in current list a 
	 * @param c the contact that is supposed to be removed, has to be in list
	 */
	public void removeContact(Contact c) {

	}

}