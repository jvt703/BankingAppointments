package dev.n1t.appointments.service;

import dev.n1t.appointments.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BranchService {

    @Autowired
    private BranchRepository branchRepository;
  public List<OutgoingBranchDTO> getAllBranches(Map<String, String> queryParams){
      List<Branch> branchList = branchRepository.findAll();
      //we find all to get List<Branch> then convert that to an OUtgoing Branch DTO and return that
      Long id = null;
      String PhoneNumber = null;
      String Name = null;
      if (queryParams.containsKey("id")) {
          id = Long.parseLong(queryParams.get("id"));
      }

      if (queryParams.containsKey("Name")) {
          Name = queryParams.get("Name");
      }

      //
    List<Branch> branches = branchRepository.findAllByQueryParams(
            id,Name
    );


      return branches.stream().map(OutgoingBranchDTO::new).collect(Collectors.toList());


  };





    public OutgoingBranchDTO getBranchByID(){

      return OutgoingBranchDTO();
  }

    public OutgoingBranchDTO getBranchesByName(){

        return OutgoingBranchDTO();
    }




}
