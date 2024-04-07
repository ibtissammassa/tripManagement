package com.application.tripmanagementsystem.controllers;


import com.application.tripmanagementsystem.dtos.DriverDto;
import com.application.tripmanagementsystem.entities.DriverEntity;
import com.application.tripmanagementsystem.mappers.DriverMapper;
import com.application.tripmanagementsystem.services.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
public class DriverController {
    private final DriverMapper driverMapper;
    private final DriverService driverService;

    public DriverController(DriverMapper driverMapper, DriverService driverService) {
        this.driverMapper = driverMapper;
        this.driverService = driverService;
    }

    // Adding/Updating a new driver (put because the id is not automatically generated)
    @PutMapping("/drivers/{registrationNum}")
    public ResponseEntity<DriverDto> createUpdateDriver(@PathVariable("registrationNum") Long registrationNum, @RequestBody DriverDto driverDto){
        boolean driverExists = driverService.isExist(registrationNum);
        DriverEntity driverEntity = driverMapper.mapFrom(driverDto);
        DriverEntity savedDriverEntity = driverService.createUpdateDriver(driverEntity, registrationNum);
        DriverDto savedDriverDto = driverMapper.mapTo(savedDriverEntity);
        if(driverExists){
            return new ResponseEntity<>(savedDriverDto, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(savedDriverDto, HttpStatus.CREATED);
        }

    }
    // Getting a driver from the database by id
    @GetMapping("/drivers/{registrationNum}")
    public ResponseEntity<DriverDto> getDriver(@PathVariable("registrationNum") Long registrationsNum){
        Optional<DriverEntity> result = driverService.findDriver(registrationsNum);
        if (result.isPresent()){
            DriverDto driverDto = driverMapper.mapTo(result.get());
            return new ResponseEntity<>(driverDto, HttpStatus.FOUND);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Getting the list of all the drivers
    @GetMapping("/drivers")
    public List<DriverDto> getAllDrivers(){
        List<DriverEntity> result = driverService.findAllDrivers();
        return result.stream()
                .map(driverMapper::mapTo)
                .collect(Collectors.toList());
    }
    // Partial updating a driver
    @PatchMapping("/drivers/{registrationNum}")
    public ResponseEntity<DriverDto> updateDriver(@PathVariable("registrationNum") Long registrationNum,@RequestBody DriverDto driverDto){
        DriverEntity driverEntity = driverMapper.mapFrom(driverDto);
        DriverEntity updatedDriverEntity = driverService.updateDriver(registrationNum,driverEntity);
        DriverDto updatedDriverDto = driverMapper.mapTo(updatedDriverEntity);
        return new ResponseEntity<>(updatedDriverDto,HttpStatus.OK);
    }
    // Deleting a driver
    @DeleteMapping("/drivers/{registrationNum}")
    public ResponseEntity<DriverDto> deleteDriver(@PathVariable("registrationNum") Long registrationNum){
        driverService.removeDriver(registrationNum);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
