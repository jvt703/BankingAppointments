package dev.n1t.appointments.service;

import dev.n1t.appointments.dto.BranchRegistrationDTO;
import dev.n1t.appointments.dto.OutgoingBranchDTO;
import dev.n1t.appointments.dto.UpdateBranchRequest;
import dev.n1t.appointments.exception.BranchNotFoundException;
import dev.n1t.appointments.repository.AddressRepository;
import dev.n1t.appointments.repository.BranchRepository;
import dev.n1t.model.Address;
import dev.n1t.model.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BranchService {



    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private AddressRepository addressRepository;

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


    @Transactional
    public OutgoingBranchDTO createBranch(BranchRegistrationDTO branchRegistrationDTO) {
        try {
        var address = Address.builder()
                .city(branchRegistrationDTO.getCity())
                .state(branchRegistrationDTO.getState())
                .street(branchRegistrationDTO.getStreet())
                .zipCode(branchRegistrationDTO.getZipCode())
                .build();
        Address addressSaved = addressRepository.save(address);


        Branch branch = Branch.builder()
                .address(addressSaved)
                .phoneNumber(branchRegistrationDTO.getPhoneNumber())
                .name(branchRegistrationDTO.getName())
                .build();
        OutgoingBranchDTO createdBranch = new OutgoingBranchDTO(branch);

        return createdBranch;}
        catch (Exception e){
            throw new RuntimeException("Error creating branch", e);
        }
    }

    @Transactional
    public OutgoingBranchDTO deleteBranch(Long BranchId) {
        try {
            Optional<Branch> branch = branchRepository.findById(BranchId);
            if (branch.isPresent()) {
                branchRepository.delete(branch.get());
                return new OutgoingBranchDTO(branch.get());
            } else {
                throw new BranchNotFoundException("Branch with ID " + BranchId + " not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting branch with ID " + BranchId, e);
        }
    }

    @Transactional
    public OutgoingBranchDTO updateBranch(UpdateBranchRequest updateBranchRequest) {
        try {
            Optional<Branch> branchOptional = branchRepository.findById(updateBranchRequest.getId());
            if (!branchOptional.isPresent()) {
                throw new BranchNotFoundException("Branch with ID " + updateBranchRequest.getId() + " not found");            }
            Branch branch = branchOptional.get();

            branch.setName(updateBranchRequest.getName());
            branch.setPhoneNumber(updateBranchRequest.getPhoneNumber());

            Address address = branch.getAddress();
            address.setCity(updateBranchRequest.getCity());
            address.setState(updateBranchRequest.getState());
            address.setStreet(updateBranchRequest.getStreet());
            address.setZipCode(updateBranchRequest.getZipCode());

            Branch updatedBranch = branchRepository.save(branch);

            return new OutgoingBranchDTO(updatedBranch);
        } catch (Exception e) {
            throw new RuntimeException("Error updating branch with ID " + updateBranchRequest.getId(), e);
        }
    }


}
