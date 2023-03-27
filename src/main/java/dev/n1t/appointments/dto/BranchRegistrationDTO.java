package dev.n1t.appointments.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BranchRegistrationDTO {
    private String name;
    private String city;
    private String street;
    private String state;
    private String zipCode;
    private String phoneNumber;
}
