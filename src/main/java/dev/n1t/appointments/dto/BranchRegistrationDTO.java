package dev.n1t.appointments.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BranchRegistrationDTO {
    private String name;
    private AddressDTO address;
    private String phoneNumber;
}
