package dev.n1t.appointments.repository;

import dev.n1t.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT a FROM Appointment a WHERE a.branch.id = :branchId AND a.active = true AND a.appointmentDateTime > :startTime AND a.appointmentDateTime <     :endTime")
    List<Appointment> findByBranchAndAppointmentDateTimeWithinRange(Long branchId, Long startTime, Long endTime);

    List<Appointment> findByUserId(Long userId);
}
