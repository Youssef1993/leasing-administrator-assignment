package com.leasing.administrator.leasingadministratorbackend.controllers;

import com.leasing.administrator.leasingadministratorbackend.dtos.VehicleDTO;
import com.leasing.administrator.leasingadministratorbackend.entities.Vehicle;
import com.leasing.administrator.leasingadministratorbackend.services.VehicleService;
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
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @Operation(summary = "Creates a new Vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created Vehicle"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    @PostMapping
    public ResponseEntity<VehicleDTO> createVehicle(@RequestBody @Valid VehicleDTO vehicleDTO) {
        Vehicle vehicle = this.vehicleService.createVehicle(vehicleDTO.toEntity());
        return new ResponseEntity<>(new VehicleDTO(vehicle), HttpStatus.CREATED);
    }

    @Operation(summary = "Returns List Of Vehicle")
    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAllVehicles(@RequestParam(defaultValue = "0", required = false, name = "withoutContract") boolean withoutContract) {
        List<Vehicle> vehicles;
        if (withoutContract) {
            vehicles = this.vehicleService.getVehiclesWithoutContract();
        } else  {
            vehicles = this.vehicleService.getAllVehicles();
        }
        List<VehicleDTO> vehicleDTOS = vehicles.stream().map(VehicleDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(vehicleDTOS, HttpStatus.OK);
    }

    @Operation(summary = "Update Existing Vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Vehicle updated"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    @PutMapping
    public ResponseEntity<Void> editVehicle(@RequestBody @Valid VehicleDTO vehicleDTO) {
        this.vehicleService.editVehicle(vehicleDTO.toEntity());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
