package bishopfish.scheduler;

import jfxtras.scene.control.agenda.Agenda;

public class Appointment extends Agenda.AppointmentImplLocal{

    //identifier of the appointment
    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //TODO: Conversion from appointment to booking entry and vice versa
/*    public BookingEntry appointmentToBookingEntry(){

    }*/
}
