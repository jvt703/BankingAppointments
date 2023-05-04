package dev.n1t.appointments.service;

import dev.n1t.appointments.repository.ServiceTypeRepository;
import dev.n1t.model.ServiceType;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class ServiceTypeService {


    @Autowired
    private ServiceTypeRepository serviceTypeRepository;


    public List<ServiceType> getAllServiceTypes() {
        try {
            return serviceTypeRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error getting all service types", e);
        }
    }
    public Optional<ServiceType> getServiceTypeById(Long serviceTypeId) {
        try {
            return serviceTypeRepository.findById(serviceTypeId);
        } catch (Exception e) {
            throw new RuntimeException("Error getting service type with ID " + serviceTypeId, e);
        }
    }
    @Transactional
    public ServiceType createServiceType(ServiceType serviceType) {
        try {
            return serviceTypeRepository.save(serviceType);
        } catch (Exception e) {
            throw new RuntimeException("Error creating service type", e);
        }
    }

    @Transactional
    public ServiceType updateServiceType(ServiceType serviceType) {
        try {
            Optional<ServiceType> existingServiceTypeOptional = serviceTypeRepository.findById(serviceType.getId());
            if (!existingServiceTypeOptional.isPresent()) {
                throw new IllegalArgumentException("Service type with ID " + serviceType.getId() + " not found");
            }
            return serviceTypeRepository.save(serviceType);
        } catch (Exception e) {
            throw new RuntimeException("Error updating service type with ID " + serviceType.getId(), e);
        }
    }

    @Transactional
    public void deleteServiceType(Long serviceTypeId) {
        try {
            Optional<ServiceType> serviceTypeOptional = serviceTypeRepository.findById(serviceTypeId);
            if (serviceTypeOptional.isPresent()) {
                serviceTypeRepository.deleteById(serviceTypeId);
            } else {
                throw new IllegalArgumentException("Service type with ID " + serviceTypeId + " not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting service type with ID " + serviceTypeId, e);
        }
    }


}
