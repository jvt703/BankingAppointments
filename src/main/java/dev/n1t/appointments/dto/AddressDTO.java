package dev.n1t.appointments.dto;

public class AddressDTO {
    private String city;
    private String street;
    private String state;
    private String zipCode;

    public AddressDTO(String city, String street, String state, String zipCode) {
        this.city = city;
        this.street = street;
        this.state = state;
        this.zipCode = zipCode;
    }
}
