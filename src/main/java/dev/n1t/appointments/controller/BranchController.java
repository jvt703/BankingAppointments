package dev.n1t.appointments.controller;

import dev.n1t.appointments.dto.BranchRegistrationDTO;
import dev.n1t.appointments.dto.OutgoingBranchDTO;
import dev.n1t.appointments.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BranchController {


    private final BranchService branchService;


    @Autowired
    public BranchController(BranchService branchService){
        this.branchService = branchService;
    }

    @GetMapping(path = "/branch/all", produces = "application/json")
    public ResponseEntity<List<OutgoingBranchDTO>>  getBranches(
            @RequestParam Map<String, String> queryParams
    ){
        List<OutgoingBranchDTO> response =  branchService.getAllBranches(queryParams);
        return ResponseEntity.ok(response);
    };

    @GetMapping(path = "/branch/{branchId}", produces = "application/json")
    public ResponseEntity<OutgoingBranchDTO> getBranchById(
            @PathVariable(value = "branchId") long branchId
    ){
        return ResponseEntity.ok(branchService.getBranchById(branchId));
    };

    @PostMapping("/branch/create")
    public ResponseEntity<OutgoingBranchDTO> createBranch(
            @Validated @RequestBody BranchRegistrationDTO branchRegistrationDTO
    ){
        return ResponseEntity.ok(branchService.createBranch(branchRegistrationDTO));
    }

}
