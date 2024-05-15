package com.application.tripmanagementsystem.services.impl;

import com.application.tripmanagementsystem.dtos.DriverDto;
import com.application.tripmanagementsystem.dtos.TripDto;
import com.application.tripmanagementsystem.dtos.VehicleDto;
import com.application.tripmanagementsystem.entities.DriverEntity;
import com.application.tripmanagementsystem.entities.TripEntity;
import com.application.tripmanagementsystem.entities.VehicleEntity;
import com.application.tripmanagementsystem.mappers.DriverMapper;
import com.application.tripmanagementsystem.mappers.TripMapper;
import com.application.tripmanagementsystem.mappers.VehicleMapper;
import com.application.tripmanagementsystem.repositories.DriverRepository;
import com.application.tripmanagementsystem.repositories.TripRepository;
import com.application.tripmanagementsystem.repositories.VehicleRepository;
import com.application.tripmanagementsystem.services.AffectationService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AffectationServiceImpl implements AffectationService {
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final TripRepository tripRepository;
    private final DriverMapper driverMapper;
    private final VehicleMapper vehicleMapper;
    private final TripMapper tripMapper;

    public AffectationServiceImpl(DriverRepository driverRepository, VehicleRepository vehicleRepository,
                                  TripRepository tripRepository, DriverMapper driverMapper,
                                  VehicleMapper vehicleMapper, TripMapper tripMapper) {
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
        this.tripRepository = tripRepository;
        this.driverMapper = driverMapper;
        this.vehicleMapper = vehicleMapper;
        this.tripMapper = tripMapper;
    }
    @Override
    public List<DriverDto> getAvailableDrivers(Date departure, Date arrival) {
        List<DriverEntity> availableDrivers = driverRepository.findAvailableDrivers(departure, arrival);
        return availableDrivers.stream()
                .map(driverMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleDto> getAvailableVehicles(Date departure, Date arrival) {
        List<VehicleEntity> availableVehicles = vehicleRepository.findAvailableVehicles(departure, arrival);
        return availableVehicles.stream()
                .map(vehicleMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TripDto> affectToTrip(Long tripId, Long driverRegistrationNum, Long vehicleId) {
        Optional<TripEntity> tripOptional = tripRepository.findById(tripId);
        Optional<DriverEntity> driverOptional = driverRepository.findById(driverRegistrationNum);
        Optional<VehicleEntity> vehicleOptional = vehicleRepository.findById(vehicleId);

        if (tripOptional.isPresent() && driverOptional.isPresent() && vehicleOptional.isPresent()) {
            TripEntity tripEntity = tripOptional.get();
            DriverEntity driverEntity = driverOptional.get();
            VehicleEntity vehicleEntity = vehicleOptional.get();

            // Assign driver and vehicle to the trip
            tripEntity.setDriver(driverEntity);
            tripEntity.setVehicle(vehicleEntity);
            TripEntity savedTripEntity = tripRepository.save(tripEntity);

            return Optional.of(tripMapper.mapTo(savedTripEntity));
        } else {
            return Optional.empty();
        }
    }
    }

