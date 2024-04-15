package com.application.tripmanagementsystem.services;

import com.application.tripmanagementsystem.entities.VehicleEntity;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    // crud operations for vehicle table
    VehicleEntity createUpdateVehicle(VehicleEntity vehicle, Long id);
    VehicleEntity updateVehicle(Long id,VehicleEntity vehicle);
    Optional<VehicleEntity> findVehicle(Long id);
    List<VehicleEntity> findAllVehicles();
    void removeVehicle(Long id);
    boolean isExist(Long id);
}
