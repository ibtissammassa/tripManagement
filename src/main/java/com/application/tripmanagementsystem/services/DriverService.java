package com.application.tripmanagementsystem.services;

import com.application.tripmanagementsystem.entities.DriverEntity;

import java.util.List;
import java.util.Optional;


public interface DriverService {
    // provides basic crud operations for drivers table
    DriverEntity createUpdateDriver(DriverEntity driver, Long registrationNum);
    DriverEntity updateDriver(Long registrationNum, DriverEntity driver);
    Optional<DriverEntity> findDriver(Long registrationNum);
    List<DriverEntity> findAllDrivers();
    void removeDriver(Long registrationNum);
    boolean isExist(Long registrationNum);

}
