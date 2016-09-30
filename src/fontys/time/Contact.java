/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Paul
 */
public class Contact {

    private String name;
    private List<Appointment> agenda;

    public Contact(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    protected boolean addAppointment(Appointment a) {
        for(Appointment appointment: agenda){
            //if (appointment timespan intersects with a timespan) {
              return false;  
            //}
        }
        this.agenda.add(a);
        return true;
    }
    
    protected void removeAppointment(Appointment a){
        this.agenda.remove(a);
    }
    
   // public Iterator<Appointment> appointments(){
     //   return this.agenda;
    //}
}
