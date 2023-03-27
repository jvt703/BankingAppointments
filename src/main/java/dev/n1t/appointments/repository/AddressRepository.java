package dev.n1t.appointments.repository;

import dev.n1t.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Optional<Address> findAddressById(Integer id);
}
