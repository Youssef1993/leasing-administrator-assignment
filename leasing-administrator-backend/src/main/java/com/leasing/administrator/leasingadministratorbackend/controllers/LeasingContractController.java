package com.leasing.administrator.leasingadministratorbackend.controllers;

import com.leasing.administrator.leasingadministratorbackend.dtos.LeasingContractDTO;
import com.leasing.administrator.leasingadministratorbackend.entities.LeasingContract;
import com.leasing.administrator.leasingadministratorbackend.services.LeasingContractService;
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

    @PostMapping
    public ResponseEntity<LeasingContractDTO> createLeasingContract(@RequestBody @Valid LeasingContractDTO leasingContractDTO) {
        LeasingContract leasingContract = this.leasingContractService.createLeasingContract(leasingContractDTO.toEntity());
        return new ResponseEntity<>(new LeasingContractDTO(leasingContract), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LeasingContractDTO>> getAllContracts(@RequestParam(defaultValue = "0", required = false, name = "withoutContract") boolean withoutContract) {
        List<LeasingContract> contracts = this.leasingContractService.getAllLeasingContracts();
        List<LeasingContractDTO> contractDTOS = contracts.stream().map(LeasingContractDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(contractDTOS, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> editVehicle(@RequestBody @Valid LeasingContractDTO vehicleDTO) {
        this.leasingContractService.editLeasingContract(vehicleDTO.toEntity());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
