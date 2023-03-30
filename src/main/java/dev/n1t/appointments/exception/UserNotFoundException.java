package dev.n1t.appointments.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String userId){
        super(String.format("User with Id %d not found", userId));
    }
}
