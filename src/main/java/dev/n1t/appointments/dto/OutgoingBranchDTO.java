package dev.n1t.appointments.dto;

import dev.n1t.model.Branch;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutgoingBranchDTO {
    private Long id;
    private String Name;

    private AddressDTO Address;

    private String PhoneNumber;

    public OutgoingBranchDTO(Branch branch){

        this.id = branch.getId();
        this.Name = branch.getName();
        this.PhoneNumber = branch.getPhoneNumber();
        this.Address = new AddressDTO(branch.getAddress().getCity()
                , branch.getAddress().getStreet()
                , branch.getAddress().getState()
                ,branch.getAddress().getZipCode());

    }




}
