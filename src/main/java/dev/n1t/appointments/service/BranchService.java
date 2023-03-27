package dev.n1t.appointments.service;

import dev.n1t.appointments.dto.OutgoingBranchDTO;
import dev.n1t.appointments.exception.BranchNotFoundException;
import dev.n1t.appointments.repository.BranchRepository;
import dev.n1t.model.Branch;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BranchService {



    @Autowired
    private BranchRepository branchRepository;
    public List<OutgoingBranchDTO> getAllBranches(Map<String, String> queryParams) {
        try {
            Long id = null;
            String name = null;

            if (queryParams.containsKey("id")) {
                id = Long.parseLong(queryParams.get("id"));
            }

            if (queryParams.containsKey("name")) {
                name = queryParams.get("name");
            }

            List<Branch> branches = branchRepository.findAllByQueryParams(id, name);
            return branches.stream().map(OutgoingBranchDTO::new).collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid ID parameter", e);
        } catch (Exception e) {
            throw new RuntimeException("Error getting branches", e);
        }
    }





    public OutgoingBranchDTO getBranchById(long branchId) {
        try {
            Optional<Branch> branch = branchRepository.findById(branchId);
            if (branch.isPresent()) {
                return new OutgoingBranchDTO(branch.get());
            } else {
                throw new BranchNotFoundException("Branch with ID " + branchId + " not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting branch with ID " + branchId, e);
        }
    }





}
