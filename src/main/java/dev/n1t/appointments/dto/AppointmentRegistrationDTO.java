package dev.n1t.appointments.dto;

import dev.n1t.model.Branch;
import dev.n1t.model.ServiceType;
import dev.n1t.model.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
public class AppointmentRegistrationDTO {


    private int userId;

    private long branchId;

    private Long appointmentDateTime;

    private int bankerId;

    private Long serviceTypeId;


}
