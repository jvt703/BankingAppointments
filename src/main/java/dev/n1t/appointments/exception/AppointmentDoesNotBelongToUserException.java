package dev.n1t.appointments.exception;

public class AppointmentDoesNotBelongToUserException extends RuntimeException{


    public AppointmentDoesNotBelongToUserException(Long appointmentId, Integer userId){
        super(String.format("Appointment with id %d does not belong to user of id %d", appointmentId, userId));

    }
}
