package dev.n1t.appointments.repository;

import dev.n1t.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findAll();
    @Query("SELECT b FROM Branch b WHERE "
            + "(:id IS NULL OR b.id = :id) "
            + "AND (:name IS NULL OR b.name LIKE %:name%)")
    List<Branch> findAllByQueryParams(
            @Param("id") Long id,
            @Param("name") String name);
}
