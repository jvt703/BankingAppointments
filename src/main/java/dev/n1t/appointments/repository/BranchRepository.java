package dev.n1t.appointments.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<> {
    List<Branch> findAll();


}
