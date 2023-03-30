package dev.n1t.appointments.repository;

import dev.n1t.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

        Optional<User> findById(Integer id);

        Optional<User> findByEmail(String email);
        }
