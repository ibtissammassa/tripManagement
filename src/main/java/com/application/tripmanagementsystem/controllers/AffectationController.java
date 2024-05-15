package com.application.tripmanagementsystem.controllers;

import com.application.tripmanagementsystem.dtos.DriverDto;
import com.application.tripmanagementsystem.dtos.TripDto;
import com.application.tripmanagementsystem.dtos.VehicleDto;
import com.application.tripmanagementsystem.services.AffectationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class AffectationController {

    private final AffectationService affectationService;

    public AffectationController(AffectationService affectationService) {
        this.affectationService = affectationService;
    }

    @GetMapping("/availableDrivers")
    public ResponseEntity<List<DriverDto>> getAvailableDrivers(
            @RequestParam("departure") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date departure,
            @RequestParam("arrival") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date arrival) {
        List<DriverDto> availableDrivers = affectationService.getAvailableDrivers(departure, arrival);
        return ResponseEntity.ok(availableDrivers);
    }

    @GetMapping("/availableVehicles")
    public ResponseEntity<List<VehicleDto>> getAvailableVehicles(
            @RequestParam("departure") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date departure,
            @RequestParam("arrival") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date arrival) {
        List<VehicleDto> availableVehicles = affectationService.getAvailableVehicles(departure, arrival);
        return ResponseEntity.ok(availableVehicles);
    }

    @PostMapping("/assignTrip/{tripId}/driver/{driverRegistrationNum}/vehicle/{vehicleId}")
    public ResponseEntity<TripDto> assignTrip(
            @PathVariable("tripId") Long tripId,
            @PathVariable("driverRegistrationNum") Long driverRegistrationNum,
            @PathVariable("vehicleId") Long vehicleId) {
        Optional<TripDto> assignedTrip = affectationService.affectToTrip(tripId, driverRegistrationNum, vehicleId);
        return assignedTrip.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
