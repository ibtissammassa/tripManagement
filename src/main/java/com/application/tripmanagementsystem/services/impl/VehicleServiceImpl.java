package com.application.tripmanagementsystem.services.impl;

import com.application.tripmanagementsystem.entities.VehicleEntity;
import com.application.tripmanagementsystem.repositories.VehicleRepository;
import com.application.tripmanagementsystem.services.VehicleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }
    @Override
    public VehicleEntity createUpdateVehicle(VehicleEntity vehicle, Long id) {
        vehicle.setId(id);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public VehicleEntity updateVehicle(Long id, VehicleEntity vehicle) {
        vehicle.setId(id);
        return vehicleRepository.findById(id).map(existingVehicle -> {
            Optional.ofNullable(vehicle.getVehicleType()).ifPresent(existingVehicle::setVehicleType);
            Optional.ofNullable(vehicle.getAvailable()).ifPresent(existingVehicle::setAvailable);
            Optional.ofNullable(vehicle.getBrand()).ifPresent(existingVehicle::setBrand);
            Optional.ofNullable(vehicle.getMileage()).ifPresent(existingVehicle::setMileage);
            Optional.ofNullable(vehicle.getModel()).ifPresent(existingVehicle::setModel);
            Optional.ofNullable(vehicle.getRequiredLicenceType()).ifPresent(existingVehicle::setRequiredLicenceType);
            Optional.ofNullable(vehicle.getSpecialEquipments()).ifPresent(existingVehicle::setSpecialEquipments);
            Optional.ofNullable(vehicle.getGrayCard()).ifPresent(existingVehicle::setGrayCard);
            return vehicleRepository.save(existingVehicle);
        }).orElseThrow(() -> new RuntimeException("Vehicle doesn't exist"));
    }

    @Override
    public Optional<VehicleEntity> findVehicle(Long id) {
        return vehicleRepository.findById(id);
    }

    @Override
    public List<VehicleEntity> findAllVehicles() {
        return StreamSupport
                .stream(vehicleRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public void removeVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Long id) {
        return vehicleRepository.existsById(id);
    }
}
