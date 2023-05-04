package dev.n1t.appointments.service;

import dev.n1t.appointments.repository.*;
import dev.n1t.model.*;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class DummyDataInitializer {

private final BranchRepository branchRepository;
private final ServiceTypeRepository serviceTypeRepository;
private final AddressRepository addressRepository;
private final AppointmentRepository appointmentRepository;
private final RoleRepository roleRepository;

private final UserRepository userRepository;


        @Autowired
        public DummyDataInitializer(BranchRepository branchRepository, UserRepository userRepository, RoleRepository roleRepository, ServiceTypeRepository serviceTypeRepository,AddressRepository addressRepository, AppointmentRepository appointmentRepository){
        this.addressRepository=addressRepository;
        this.branchRepository=branchRepository;
        this.serviceTypeRepository=serviceTypeRepository;
        this.appointmentRepository=appointmentRepository;
        this.roleRepository=roleRepository;
        this.userRepository= userRepository;

            Role inputUserRole = Role.builder()
                    .roleName("Admin")
                    .build();
            Role inputUserRole2 = Role.builder()
                    .roleName("User")
                    .build();
            Role outputUserRole = roleRepository.save(inputUserRole);
            Role outputUserRole2 = roleRepository.save(inputUserRole2);

        Address inputUserAddress = Address.builder()
                    .city("Boston")
                    .zipCode("02116")
                    .state("Massachusetts")
                    .street("70 Worcester St")
                    .build();
        Address outputUserAddress = addressRepository.save(inputUserAddress);

        Branch branch = Branch.builder()
                    .address(inputUserAddress)
                    .name("TestBranch")
                    .phoneNumber("777777777")
                    .build();
        Branch branchCreated = branchRepository.save(branch);

        ServiceType serviceType = ServiceType.builder()
                    .typeName("Deposit")
                    .description("Deposit Cash")
                    .build();
        ServiceType outputServiceType = serviceTypeRepository.save(serviceType);
        User inputUser = User.builder()
                    .active(true)
                    .email("mario.mario@email.com")
                    .address(outputUserAddress)
                    .firstname("Mario")
                    .lastname("Mario")
                    .role(outputUserRole2)
                    .emailValidated(false)
                    .password("Mario")
                    .birthDate(Date.valueOf("1981-07-09").getTime()) .build();
        User outputUser = userRepository.save(inputUser);

            Appointment appointment = Appointment.builder()
                    .appointmentDateTime(1683165957l)
                    .branch(branchRepository.findById(1l).get())
                    .user(outputUser)
                    .serviceType(serviceTypeRepository.findById(outputServiceType.getId()).get())
                    .banker(outputUser)
                    .active(true)
                    .build();
            Appointment appointment1 = appointmentRepository.save(appointment);
        }


}
