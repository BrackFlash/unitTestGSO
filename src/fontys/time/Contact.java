/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Paul
 */
public class Contact {

    /**
     * Name of a contact
     */
    private String name;

    /**
     * The agenda of a contact
     */
    private ArrayList<Appointment> agenda;

    /**
     * Constructor of the contact class initializes the name and agenda
     *
     * @param name
     */
    public Contact(String name) {
        this.name = name;
        this.agenda = new ArrayList<>();
    }

    /**
     *
     * @return Returns the name of the contact
     */
    public String getName() {
        return this.name;
    }

    /**
     * Adds an appointment to the agenda of the contact
     *
     * @param a The appointment that is trying to be added to the agenda
     * @return If an appointment is succesfully added then the method will
     * return true, otherwise false
     */
    protected boolean addAppointment(Appointment a) {
        for (Iterator<Appointment> app = appointments(); app.hasNext();) {
            if (app.next().getTimeSpan().intersectionWith(a.getTimeSpan()) != null) {
                return false;
            }
        }

        this.agenda.add(a);
        return true;
    }

    /**
     * Removes the appointment in the agenda of the current object
     * @param a 
     */
    protected void removeAppointment(Appointment a) {
        this.agenda.remove(a);
    }

    /**
     * 
     * @return Returns an iterator for the agenda Arraylist
     */
    public Iterator<Appointment> appointments() {
        return agenda.iterator();
    }

}
