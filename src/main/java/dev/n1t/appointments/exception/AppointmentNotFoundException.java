package dev.n1t.appointments.exception;

public class AppointmentNotFoundException extends RuntimeException{
    public AppointmentNotFoundException(Long appointmentId){
        super(String.format("Appointment with id %d not found",appointmentId ));
    }
}
