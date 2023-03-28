package dev.n1t.appointments;

import dev.n1t.appointments.dto.OutgoingBranchDTO;
import dev.n1t.appointments.exception.BranchNotFoundException;
import dev.n1t.appointments.repository.AddressRepository;
import dev.n1t.appointments.repository.BranchRepository;
import dev.n1t.appointments.service.BranchService;
import dev.n1t.model.Address;
import dev.n1t.model.Branch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class branchTests {
    @Mock
    private BranchRepository branchRepository;
    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private BranchService branchService;

    private Map<String, String> queryParams;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        queryParams = new HashMap<>();
    }

    @Test
    void getAllBranches_withNoParams_shouldReturnAllBranches() {

        List<Branch> branches = List.of(
                new Branch(1L, new Address(), "Branch 1", "1234567890"),
                new Branch(2L, new Address(), "Branch 2", "1234567890")
        );
        when(branchRepository.findAllByQueryParams(null, null)).thenReturn(branches);

        List<OutgoingBranchDTO> result = branchService.getAllBranches(queryParams);

        assertEquals(2, result.size());
        verify(branchRepository).findAllByQueryParams(null, null);
    }
    @Test
    void getAllBranches_withIdParam_shouldReturnMatchingBranch() {

        queryParams.put("id", "1");
        List<Branch> branches = List.of(
                new Branch(1L, new Address(), "Branch 1", "1234567890")
        );
        when(branchRepository.findAllByQueryParams(1L, null)).thenReturn(branches);

        List<OutgoingBranchDTO> result = branchService.getAllBranches(queryParams);

        assertEquals(1, result.size());
        verify(branchRepository).findAllByQueryParams(1L, null);
    }
    @Test
    void getAllBranches_withNameParam_shouldReturnMatchingBranch() {

        queryParams.put("name", "Branch 1");
        List<Branch> branches = List.of(
                new Branch(1L, new Address(), "Branch 1", "1234567890")
        );
        when(branchRepository.findAllByQueryParams(null, "Branch 1")).thenReturn(branches);
        List<OutgoingBranchDTO> result = branchService.getAllBranches(queryParams);

        assertEquals(1, result.size());
        verify(branchRepository).findAllByQueryParams(null, "Branch 1");
    }
    @Test
    void getBranchById_withValidId_shouldReturnMatchingBranch() {
        when(branchRepository.findById(1L)).thenReturn(Optional.of(new Branch(1L, new Address(),  "1234567890","Branch 1")));
        OutgoingBranchDTO result = branchService.getBranchById(1L);

        assertEquals("Branch 1", result.getName());
        verify(branchRepository).findById(1L);
    }
    @Test
    void getBranchById_withInvalidId_shouldThrowException() {

        when(branchRepository.findById(1L)).thenReturn(Optional.empty());


        assertThrows(BranchNotFoundException.class, () -> branchService.getBranchById(1L));

    }
}
