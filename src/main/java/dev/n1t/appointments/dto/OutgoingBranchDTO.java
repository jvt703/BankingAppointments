package dev.n1t.appointments.dto;

public class OutgoingBranchDTO {
    private Long id;
    private String Name;

    private String Address;

    private String PhoneNumber;

    public OutgoingBranchDTO(Branch branch){

        this.id = branch.getId();
        this.Name = branch.getName();
        this.PhoneNumber = branch.getPhoneNumber();
        this.Address = branch.getAddress().getCity() + branch.getAddress().getState();

    }




}
