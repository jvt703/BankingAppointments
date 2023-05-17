package dev.n1t.appointments.exception;

public class InvalidAppointmentTimeException extends RuntimeException{

    public InvalidAppointmentTimeException(Long appointmentId){
        super(String.format("Appointment time %d invalid",appointmentId ));
    }
}
