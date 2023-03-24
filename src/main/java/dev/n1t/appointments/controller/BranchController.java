package dev.n1t.appointments.controller;

import dev.n1t.appointments.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BranchController {


    private final BranchService branchService;

    @GetMapping(path = "/branch/all", produces = "application/json")
    public List<OutgoingBranchDTO> getBranches(

    ){
        return ResponseEntity.ok(branchService.getAllBranches());
    };

    @GetMapping(path = "/branch/{branchId}", produces = "application/json")
    public List<OutgoingBranchDTO> getBranches(

    ){
        return ResponseEntity.ok(branchService.getBrancheByID());
    };
}
