package dev.n1t.appointments.controller;

import dev.n1t.appointments.dto.BranchRegistrationDTO;
import dev.n1t.appointments.dto.OutgoingBranchDTO;
import dev.n1t.appointments.service.BranchService;
import dev.n1t.appointments.service.DummyDataInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BranchController {

    @Autowired
    private final DummyDataInitializer dummyDataInitializer;
    private final BranchService branchService;


    @Autowired
    public BranchController(BranchService branchService, DummyDataInitializer dummyDataInitializer){
        this.branchService = branchService;
        this.dummyDataInitializer = dummyDataInitializer;
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
