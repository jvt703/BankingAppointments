package dev.n1t.appointments.controller;

import dev.n1t.appointments.service.ServiceTypeService;
import dev.n1t.model.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/servicetype")
public class ServiceTypeController {

    @Autowired
    private ServiceTypeService serviceTypeService;


    @GetMapping("/serviceTypes")
    public ResponseEntity<List<ServiceType>> getAllServiceTypes() {
        List<ServiceType> serviceTypes = serviceTypeService.getAllServiceTypes();
        return ResponseEntity.ok(serviceTypes);
    }
}
