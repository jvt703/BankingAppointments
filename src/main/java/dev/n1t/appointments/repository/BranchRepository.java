package dev.n1t.appointments.repository;

import dev.n1t.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findAll(Branch branch);
    @Query ("SELECT a FROM Branch a WHERE "
         + "(:id IS NULL OR a.id = :id) "
         + "AND (:Name IS NULL OR a.branch.name LIKE %:Name%) ")
    List<Branch> findAllByQueryParams(
            @Param("id") Long id, @Param("Name") String Name);


}
