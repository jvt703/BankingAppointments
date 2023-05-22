package dev.n1t.appointments.service;


import dev.n1t.appointments.dto.AppointmentRegistrationDTO;
import dev.n1t.appointments.dto.OutgoingAppointmentDTO;
import dev.n1t.appointments.exception.AppointmentDoesNotBelongToUserException;
import dev.n1t.appointments.exception.AppointmentNotFoundException;
import dev.n1t.appointments.exception.InvalidAppointmentTimeException;
import dev.n1t.appointments.exception.UserNotFoundException;
import dev.n1t.appointments.repository.AppointmentRepository;
import dev.n1t.appointments.repository.BranchRepository;
import dev.n1t.appointments.repository.ServiceTypeRepository;
import dev.n1t.appointments.repository.UserRepository;
import dev.n1t.model.Appointment;
import dev.n1t.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;
    @Autowired
    private BranchRepository branchRepository;
    public List<OutgoingAppointmentDTO> getAllAppointments() {
        try {
            List<Appointment> appointments = appointmentRepository.findAll();
            return appointments.stream().map(OutgoingAppointmentDTO::new).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error getting appointments", e);
        }
    }

    public OutgoingAppointmentDTO getAppointmentById(long appointmentId) {
        try {
            Appointment appointment = appointmentRepository.findById(appointmentId)
                    .orElseThrow(() -> new AppointmentNotFoundException(appointmentId));
            return new OutgoingAppointmentDTO(appointment);
        } catch (Exception e) {
            throw new RuntimeException("Error getting appointment with ID " + appointmentId, e);
        }
    }
// need to add branch repostiory to get and add the ranch

    public List<OutgoingAppointmentDTO> getAppointmentsByUserId(Long userId) {
        try {
            List<Appointment> appointments = appointmentRepository.findByUserId((userId));
            List<OutgoingAppointmentDTO> outgoingAppointments = appointments.stream()
                    .map(OutgoingAppointmentDTO::new)
                    .collect(Collectors.toList());
            return outgoingAppointments;
        } catch (Exception e) {
            throw new RuntimeException("Error getting appointments for user with ID " + userId, e);
        }
    }

    public List<OutgoingAppointmentDTO> getAppointmentsByBranchAndTimeRange(Long branchId, Long startTime, Long endTime) {
        try {
            List<Appointment> appointments = appointmentRepository.findByBranchAndAppointmentDateTimeWithinRange(branchId, startTime, endTime);
            List<OutgoingAppointmentDTO> outgoingAppointments = appointments.stream()
                    .map(OutgoingAppointmentDTO::new)
                    .collect(Collectors.toList());
            return outgoingAppointments;
        } catch (Exception e) {
            throw new RuntimeException("Error getting appointments for branch with ID " + branchId, e);
        }
    }

    public boolean hasAppointmentsWithin30Mins(Long branchId, Long appointmentDateTime) {
        Long startTime = appointmentDateTime - 30 * 60 ;
        Long endTime = appointmentDateTime + 30 * 60 ;
        List<Appointment> appointments = appointmentRepository.findByBranchAndAppointmentDateTimeWithinRange(branchId, startTime, endTime);
        System.out.println(appointments.toString());

        return appointments.isEmpty();
    }
    @Transactional
    public OutgoingAppointmentDTO createAppointment(AppointmentRegistrationDTO appointmentDTO) {
        System.out.println(appointmentDTO.getBranchId());
        System.out.println(appointmentDTO.getAppointmentDateTime());

        //before we create we must find with same branch and time
       boolean timeIsValid = hasAppointmentsWithin30Mins(appointmentDTO.getBranchId(), appointmentDTO.getAppointmentDateTime());
        if(timeIsValid){
            Appointment appointment = Appointment.builder()
                    .user(userRepository.findById(appointmentDTO.getUserId())
                            .orElseThrow(() -> new UserNotFoundException(appointmentDTO.getUserId())))
                    .branch( branchRepository.findById(appointmentDTO.getBranchId()).get())
                    .appointmentDateTime(appointmentDTO.getAppointmentDateTime())
                    .banker(userRepository.findById(appointmentDTO.getBankerId()).get())
                    .serviceType(serviceTypeRepository.findById(appointmentDTO.getServiceTypeId()).get())
                    .active(true)
                    .build();
            Appointment createdAppointment = appointmentRepository.save(appointment);
            return new OutgoingAppointmentDTO(createdAppointment);
        }
        else throw new InvalidAppointmentTimeException(appointmentDTO.getAppointmentDateTime());
    }

    @Transactional
    public OutgoingAppointmentDTO deleteAppointment(Long appointmentId, Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
        if (user.isPresent()) {
            if (appointment.isPresent()) {
                if (appointment.get().getUser().equals(user.get())){
                    appointmentRepository.delete(appointment.get());}
                else throw new AppointmentDoesNotBelongToUserException(appointmentId,userId);
            } else throw new AppointmentNotFoundException(appointmentId);
        } else throw new UserNotFoundException(userId);
        return new OutgoingAppointmentDTO(appointment.get());
    }

//    @Transactional
//    public OutgoingAppointmentDTO updateAppointment(AppointmentDTO appointmentDTO) {
//        try {
//            Appointment appointment = appointmentRepository.findById(appointmentDTO.getId())
//                    .orElseThrow(() -> new AppointmentNotFoundException("Appointment with ID " + appointmentDTO.getId() + " not found"));
//
//            appointment.setUser(appointmentDTO.getUser());
//            appointment.setBranch(appointmentDTO.getBranch());
//            appointment.setAppointmentDateTime(appointmentDTO.getAppointmentDateTime());
//            appointment.setBanker(appointmentDTO.getBanker());
//            appointment.setServiceType(appointmentDTO.getServiceType());
//            appointment.setActive(appointmentDTO.isActive());
//
//            Appointment updatedAppointment = appointmentRepository.save(appointment);
//            return new AppointmentDTO(updatedAppointment);
//        } catch (Exception e) {
//            throw new RuntimeException("Error updating appointment with ID " + appointmentDTO.getId(), e);
//        }
//    }
}
