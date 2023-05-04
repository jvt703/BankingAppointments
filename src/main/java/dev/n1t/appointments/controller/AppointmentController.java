package dev.n1t.appointments.controller;


import dev.n1t.appointments.dto.AppointmentRegistrationDTO;
import dev.n1t.appointments.dto.OutgoingAppointmentDTO;
import dev.n1t.appointments.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<List<OutgoingAppointmentDTO>> getAllAppointments() {
        List<OutgoingAppointmentDTO> response = appointmentService.getAllAppointments();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{appointmentId}", produces = "application/json")
    public ResponseEntity<OutgoingAppointmentDTO> getAppointmentById(
            @PathVariable(value = "appointmentId") long appointmentId
    ) {
        OutgoingAppointmentDTO response = appointmentService.getAppointmentById(appointmentId);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/create", produces = "application/json")
    public ResponseEntity<OutgoingAppointmentDTO> createAppointment(
           @RequestBody AppointmentRegistrationDTO appointmentDTO
    ) {
        OutgoingAppointmentDTO response = appointmentService.createAppointment(appointmentDTO);
        System.out.println("here2");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{appointmentId}")
    public ResponseEntity<OutgoingAppointmentDTO> deleteAppointment(
            @PathVariable(value = "appointmentId") Long appointmentId
    ) {
        OutgoingAppointmentDTO response = appointmentService.deleteAppointment(appointmentId);
        return ResponseEntity.ok(response);
    }
}