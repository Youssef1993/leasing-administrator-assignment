package com.leasing.administrator.leasingadministratorbackend.controllers;

import com.leasing.administrator.leasingadministratorbackend.dtos.LeasingContractDTO;
import com.leasing.administrator.leasingadministratorbackend.entities.LeasingContract;
import com.leasing.administrator.leasingadministratorbackend.services.LeasingContractService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/leasing-contracts")
@RequiredArgsConstructor
public class LeasingContractController {

    private final LeasingContractService leasingContractService;

    @Operation(summary = "Creates a new Leasing Contract")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created Leasing Contract"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    @PostMapping
    public ResponseEntity<LeasingContractDTO> createLeasingContract(@RequestBody @Valid LeasingContractDTO leasingContractDTO) {
        LeasingContract leasingContract = this.leasingContractService.createLeasingContract(leasingContractDTO.toEntity());
        return new ResponseEntity<>(new LeasingContractDTO(leasingContract), HttpStatus.CREATED);
    }

    @Operation(summary = "Returns List Of Leasing Contract")
    @GetMapping
    public ResponseEntity<List<LeasingContractDTO>> getAllContracts() {
        List<LeasingContract> contracts = this.leasingContractService.getAllLeasingContracts();
        List<LeasingContractDTO> contractDTOS = contracts.stream().map(LeasingContractDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(contractDTOS, HttpStatus.OK);
    }

    @Operation(summary = "Update Existing Leasing Contract")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Leasing Contract updated"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    @PutMapping
    public ResponseEntity<Void> editVehicle(@RequestBody @Valid LeasingContractDTO vehicleDTO) {
        this.leasingContractService.editLeasingContract(vehicleDTO.toEntity());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
