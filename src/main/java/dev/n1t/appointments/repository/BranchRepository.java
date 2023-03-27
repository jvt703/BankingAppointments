package dev.n1t.appointments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findAll();
    List<Branch> findAllByQueryParams(
            @Param("id") Long id, @Param("Name") String name);
//    List<Account> findAllByQueryParams(
//            @Param("id") Long id,
//            @Param("firstName") String firstName,
//            @Param("lastName") String lastName,
//            @Param("accountTypeId") Long accountTypeId,
//            @Param("active") Boolean active,
//            @Param("accountName") String accountName,
//            @Param("createdDate") Long createdDate
//    );

}
