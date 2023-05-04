package dev.n1t.appointments.dto;

import dev.n1t.model.Appointment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutgoingAppointmentDTO {
    private Long userId;

    private String branchName;

    private Long appointmentDateTime;

    private int bankerId;

    private String serviceTypeName;
    public OutgoingAppointmentDTO(Appointment appointment){
        this.userId = appointment.getId();
        this.branchName = appointment.getBranch().getName();
        this.appointmentDateTime = appointment.getAppointmentDateTime();
        this.bankerId = appointment.getBanker().getId();
        this.serviceTypeName = appointment.getServiceType().getTypeName();
    }
}
