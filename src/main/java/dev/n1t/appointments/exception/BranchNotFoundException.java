package dev.n1t.appointments.exception;

public class BranchNotFoundException extends RuntimeException{

    public BranchNotFoundException(String id){
        super(String.format("Branch with ID d% not found", id));

    }
}
