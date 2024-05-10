package com.application.tripmanagementsystem.controllers;

import com.application.tripmanagementsystem.dtos.VehicleDto;
import com.application.tripmanagementsystem.entities.GrayCardEntity;
import com.application.tripmanagementsystem.entities.VehicleEntity;
import com.application.tripmanagementsystem.mappers.VehicleMapper;
import com.application.tripmanagementsystem.services.GrayCardService;
import com.application.tripmanagementsystem.services.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class VehicleController {

    private final VehicleMapper vehicleMapper;
    private final VehicleService vehicleService;
    private final GrayCardService grayCardService;

    public VehicleController(VehicleService vehicleService,VehicleMapper vehicleMapper,GrayCardService grayCardService){
        this.vehicleMapper = vehicleMapper;
        this.vehicleService = vehicleService;
        this.grayCardService = grayCardService;
    }

    //Get all vehicles
    @GetMapping("/vehicles")
    public List<VehicleDto> getAllVehicles(){
        List<VehicleEntity> result = vehicleService.findAllVehicles();
        return result.stream().map(vehicleMapper::mapTo).collect(Collectors.toList());
    }

    //Get vehicle by id
    @GetMapping("/vehicles/{id}")
    public ResponseEntity<VehicleDto> getVehicle(@PathVariable("id") Long id){
        Optional<VehicleEntity> result = vehicleService.findVehicle(id);
        if(result.isPresent()){
            VehicleDto vehicleDto = vehicleMapper.mapTo(result.get());
            return new ResponseEntity<>(vehicleDto, HttpStatus.FOUND);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Adding Updating a new vehicle
    @PutMapping("/vehicles/{id}")
    public ResponseEntity<VehicleDto> createUpdateVehicle(@PathVariable("id") Long id, @RequestBody VehicleDto vehicleDto){
        boolean vehicleExists = vehicleService.isExist(id);
        VehicleEntity vehicleEntity = vehicleMapper.mapFrom(vehicleDto);

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

        VehicleEntity savedVehicle = vehicleService.createUpdateVehicle(vehicleEntity,id);
        VehicleDto savedVehicleDto = vehicleMapper.mapTo(savedVehicle);


        if(vehicleExists){
            System.out.println("Vehicle updated successfully.");
            return new ResponseEntity<>(savedVehicleDto,HttpStatus.OK);
        }else{
            System.out.println("Vehicle created successfully.");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    //deleting a vehicle
    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<VehicleDto> deleteVehicle(@PathVariable("id") Long id){
        vehicleService.removeVehicle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
