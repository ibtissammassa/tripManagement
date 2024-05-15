package com.application.tripmanagementsystem.controllers;

import com.application.tripmanagementsystem.dtos.TripDto;
import com.application.tripmanagementsystem.entities.DriverEntity;
import com.application.tripmanagementsystem.entities.GrayCardEntity;
import com.application.tripmanagementsystem.entities.TripEntity;
import com.application.tripmanagementsystem.entities.VehicleEntity;
import com.application.tripmanagementsystem.mappers.TripMapper;
import com.application.tripmanagementsystem.services.DriverService;
import com.application.tripmanagementsystem.services.GrayCardService;
import com.application.tripmanagementsystem.services.TripService;
import com.application.tripmanagementsystem.services.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TripController {
    private final TripMapper tripMapper;
    private final DriverService driverService;
    private final GrayCardService grayCardService;
    private final VehicleService vehicleService;
    private final TripService tripService;

    public TripController(TripMapper tripMapper, TripService tripService,DriverService driverService,VehicleService vehicleService,GrayCardService grayCardService) {
        this.tripMapper = tripMapper;
        this.tripService = tripService;
        this.driverService = driverService;
        this.vehicleService = vehicleService;
        this.grayCardService = grayCardService;
    }

    @PostMapping("/trips")
    public ResponseEntity<TripDto> createTrip(@RequestBody TripDto tripDto) {
        TripEntity tripEntity = tripMapper.mapFrom(tripDto);

        if (tripEntity.getDriver() != null){
            DriverEntity driverEntity = tripEntity.getDriver();
            if(!driverService.isExist(driverEntity.getRegistrationNum())){
                DriverEntity newDriver = driverService.createUpdateDriver(driverEntity,driverEntity.getRegistrationNum());
                tripEntity.setDriver(newDriver);
            }
        }
        if (tripEntity.getVehicle() != null){
            VehicleEntity vehicleEntity = tripEntity.getVehicle();
            // If the gray card doesn't exist we create a new one
            if (vehicleEntity.getGrayCard() != null) {
                System.out.println("gray card not null");
                GrayCardEntity grayCardEntity = vehicleEntity.getGrayCard();
                if (!grayCardService.existsById(grayCardEntity.getRegistrationNumber())) {
                    // Ensure registration number is not null or empty before creating
                    if (grayCardEntity.getRegistrationNumber() != null && !grayCardEntity.getRegistrationNumber().isEmpty()) {
                        GrayCardEntity newGrayCard = grayCardService.createGrayCard(grayCardEntity, grayCardEntity.getRegistrationNumber());
                        vehicleEntity.setGrayCard(newGrayCard);
                    } else {
                        System.out.println("Warning: Gray card registration number is null or empty.");
                    }
                }
            }
            if(!vehicleService.isExist(vehicleEntity.getId())){
                VehicleEntity newVehicle = vehicleService.createUpdateVehicle(vehicleEntity,vehicleEntity.getId());
                tripEntity.setVehicle(newVehicle);
            }
        }
        TripEntity savedTripEntity = tripService.createTrip(tripEntity);
        TripDto savedTripDto = tripMapper.mapTo(savedTripEntity);
        return new ResponseEntity<>(savedTripDto, HttpStatus.CREATED);
    }

    @GetMapping("/trips/{id}")
    public ResponseEntity<TripDto> getTrip(@PathVariable("id") Long id) {
        Optional<TripEntity> result = tripService.findTrip(id);
        if (result.isPresent()) {
            TripDto tripDto = tripMapper.mapTo(result.get());
            return new ResponseEntity<>(tripDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/trips")
    public List<TripDto> getAllTrips() {
        List<TripEntity> result = tripService.findAllTrips();
        return result.stream()
                .map(tripMapper::mapTo)
                .collect(Collectors.toList());
    }

    @PutMapping("/trips/{id}")
    public ResponseEntity<TripDto> updateTrip(@PathVariable("id") Long id, @RequestBody TripDto tripDto) {
        TripEntity tripEntity = tripMapper.mapFrom(tripDto);
        TripEntity updatedTripEntity = tripService.updateTrip(id, tripEntity);
        TripDto updatedTripDto = tripMapper.mapTo(updatedTripEntity);
        return new ResponseEntity<>(updatedTripDto, HttpStatus.OK);
    }

    @DeleteMapping("/trips/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable("id") Long id) {
        tripService.removeTrip(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
