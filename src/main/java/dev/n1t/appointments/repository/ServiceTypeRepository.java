package dev.n1t.appointments.repository;

import dev.n1t.model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long> {

List<ServiceType> findAll();




}
