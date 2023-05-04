package dev.n1t.appointments.exception;



public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(long userId){
        super(String.format("User with id %d not found", userId));
    }
}
