package dev.n1t.appointments.controller;

import dev.n1t.appointments.dto.OutgoingBranchDTO;
import dev.n1t.appointments.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<OutgoingBranchDTO> getBranches(

    ){
        return ResponseEntity.ok(branchService.getBrancheByID());
    };
}
